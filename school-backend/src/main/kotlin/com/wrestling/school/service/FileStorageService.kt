package com.wrestling.school.service

import com.wrestling.school.exceptions.FileStorageException
import com.wrestling.school.exceptions.MyFileNotFoundException
import com.wrestling.school.properties.FileStorageProperties
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption


@Service
class FileStorageService  (private val fileStorageProperties: FileStorageProperties) {
    private val log = LoggerFactory.getLogger(Application::class.java)

    private val fileStorageLocation: Path = Paths.get(fileStorageProperties.uploadDir)
            .toAbsolutePath().normalize()

    fun storeFile(file: MultipartFile): String { // Normalize file name
        val fileName = StringUtils.cleanPath(file.originalFilename!!)
        return try { // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw FileStorageException("Sorry! Filename contains invalid path sequence $fileName")
            }
            // Copy file to the target location (Replacing existing file with the same name)
            val targetLocation = fileStorageLocation.resolve(fileName)
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
            fileName
        } catch (ex: IOException) {
            throw FileStorageException("Could not store file $fileName. Please try again!", ex)
        }
    }

    fun loadFileAsResource(fileName: String): Resource {
        return try {
            val filePath = fileStorageLocation.resolve(fileName).normalize()
            val resource: Resource = UrlResource(filePath.toUri())
            if (resource.exists()) {
                resource
            } else {
                throw MyFileNotFoundException("File not found $fileName")
            }
        } catch (ex: MalformedURLException) {
            throw MyFileNotFoundException("File not found $fileName", ex)
        }
    }

    init {
        log.info("FileStorageService init invoked")
        try {
            log.info("FileStorageService fileStorageLocation is:[$fileStorageLocation]")
            Files.createDirectories(fileStorageLocation)
        } catch (ex: Exception) {
            throw FileStorageException("Could not create the directory where the uploaded files will be stored.", ex)
        }
    }
}