package com.wrestling.school.controllers

import com.wrestling.school.payload.UploadFileResponse
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
class FileController (private val fileStorageService: FileStorageService){
    private val log = LoggerFactory.getLogger(Application::class.java)

    /**
     * Загрузить файл
     */
    @PostMapping("/files/uploadFile")
    fun uploadFile(@RequestParam("file") file: MultipartFile): UploadFileResponse {
        val fileName = fileStorageService!!.storeFile(file)
        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString()
        val fileUuid = UUID.randomUUID()
        return UploadFileResponse(fileUuid, fileName, fileDownloadUri,
                file.contentType!!, file.size)
    }

    /**
     * Скачать файл как ресурс
     */
    @GetMapping("/files/downloadFile/{fileName:.+}")
    fun downloadFile(@PathVariable fileName: String?, request: HttpServletRequest): ResponseEntity<Resource> {
        val resource = fileStorageService!!.loadFileAsResource(fileName!!)

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
