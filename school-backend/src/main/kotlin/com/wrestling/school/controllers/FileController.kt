package com.wrestling.school.controllers

import com.wrestling.school.dtos.UploadFileDto
import com.wrestling.school.exceptions.MyFileNotFoundException
import com.wrestling.school.exceptions.WrongUuidException
import com.wrestling.school.models.UploadFileModel
import com.wrestling.school.repositories.UploadFileRepository
import com.wrestling.school.service.FileStorageService
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:8000"], maxAge = 4800, allowCredentials = "false")
class FileController (
        private val fileStorageService: FileStorageService,
        private val uploadFileRepository: UploadFileRepository){
    private val log = LoggerFactory.getLogger(Application::class.java)

    /**
     * Загрузить файл
     */
    @PostMapping("/files/uploadFile")
    fun uploadFile(@RequestParam("file") file: MultipartFile): UploadFileModel {
        log.info("uploadFile invoked")

        val fileUuid = UUID.randomUUID()
        val uniqFileName = fileStorageService!!.storeFile(file, fileUuid)

        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/downloadFile/")
                .path(uniqFileName.uniqFileName)
                .toUriString()

        val uploadFileModel = UploadFileModel(
                fileUuid,
                uniqFileName.originalFileName,
                uniqFileName.uniqFileName,
                fileDownloadUri,
                file.contentType!!,
                file.size)

        val uploadFileDto = UploadFileDto(
                fileUuid = uploadFileModel.fileUuid,
                originalFileName = uploadFileModel.originalFileName,
                uniqFileName = uploadFileModel.uniqFileName,
                fileDownloadUri = uploadFileModel.fileDownloadUri,
                fileType = uploadFileModel.fileType,
                size = uploadFileModel.size)

        uploadFileRepository.save(uploadFileDto);

        log.info("uploadFile completed")
        return uploadFileModel;
    }

    /**
     * Скачать файл как ресурс
     */
    @GetMapping("/files/downloadFile/{fileName:.+}")
    fun downloadFile(@PathVariable fileName: String?, request: HttpServletRequest): ResponseEntity<Resource> {
        log.info("downloadFile invoked")

        val resource = fileStorageService!!.loadFileAsResource(fileName!!)
        val result = downloadResource(resource, request)

        log.info("downloadFile completed")
        return result
    }


    /**
     * Скачать файл как ресурс по его uuid
     */
    @GetMapping("/files/downloadFileByUuid/{fileUuid}")
    fun downloadFileByUuid(@PathVariable fileUuid: String?, request: HttpServletRequest): ResponseEntity<Resource> {
        log.info("downloadFileByUuid invoked")

        val requestUuid = UUID.fromString(fileUuid)
        if (requestUuid == null)
        {
            log.error("Передан не коректный уникальный идентификатор файла. $fileUuid")
            throw WrongUuidException("Передан не коректный уникальный идентификатор файла. $fileUuid")
        }

        val uploadFileDtos = uploadFileRepository.findByFileUuid(requestUuid)

        if(uploadFileDtos == null)
        {
            log.error("Файл не найден по уникальному идентификатору. $fileUuid")
            throw MyFileNotFoundException("Файл не найден по уникальному идентификатору. $fileUuid")
        }

        if (uploadFileDtos.count() > 1)
        {
            log.warn("Найдено больше одного файла с уникальным идентификаторрм. $fileUuid")
        }
        val uploadFileDto =  uploadFileDtos.first()

        val resource = fileStorageService!!.loadFileAsResource(uploadFileDto?.uniqFileName!!)
        val result = downloadResource(resource, request)

        log.info("downloadFileByUuid completed")
        return result
    }

    private fun downloadResource(resource: Resource, request: HttpServletRequest): ResponseEntity<Resource> {
        // Попытаемся определить тип содержимого файла
        var contentType: String? = null
        try {
            contentType = request.servletContext.getMimeType(resource.file.absolutePath)
        } catch (ex: IOException) {
            log.info("Не удалось определить тип файла.")
        }

        // Устанавливаем тип контента по умолчанию, если тип не может быть определен
        if (contentType == null) {
            contentType = "application/octet-stream"
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.filename + "\"")
                .body(resource)
    }
}
