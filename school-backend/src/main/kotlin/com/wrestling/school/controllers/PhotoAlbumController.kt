package com.wrestling.school.controllers

import com.wrestling.school.dtos.PhotoAlbumDto
import com.wrestling.school.mappers.PhotoAlbumConverter
import com.wrestling.school.models.PhotoAlbumModel
import com.wrestling.school.repositories.PhotoAlbumRepository
import javafx.application.Application
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class PhotoAlbumController (private val repository: PhotoAlbumRepository) {
    private val log = LoggerFactory.getLogger(Application::class.java)

    /**
     * Возвращает все фотоальбомы
     */
    @GetMapping("/photoAlbums")
    fun findAll(): List<PhotoAlbumModel> {
        return repository.findAll().map {
            photoAlbumDto ->
            PhotoAlbumModel(
                    photoAlbumDto.id,
                    photoAlbumDto.photoAlbumTitle,
                    photoAlbumDto.photoAlbumDescription)
        }
    }

    /**
     * Возвращает пагинированный список фотоальбомов с 10-ю элементами для страницы
     */
    @GetMapping("/photoAlbums/{page}")
    fun getPage(@PathVariable page: Int): Page<PhotoAlbumModel> {
        val currentPageWithTenMessages: Pageable = PageRequest.of(page, 10)
        return repository.findAll(currentPageWithTenMessages).map {
            photoAlbumDto ->
            PhotoAlbumModel(
                    photoAlbumDto.id,
                    photoAlbumDto.photoAlbumTitle,
                    photoAlbumDto.photoAlbumDescription)
        }
    }

    /**
     * Возвращает фотоальбом по его идентификатору
     */
    @GetMapping("/photoAlbum/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<PhotoAlbumModel>? {
        return repository.findById(id).map {
            ResponseEntity.ok(PhotoAlbumModel(
                    it.id,
                    it.photoAlbumTitle,
                    it.photoAlbumDescription
            ))
        }.orElseGet { ResponseEntity.notFound().build() }
    }

    /**
     * Создаёт новый фотоальбом
     */
    @PostMapping("/photoAlbum/create")
    fun create(@RequestBody photoAlbum : PhotoAlbumModel) {
        log.info("Создание нового фотоальбома.")

        val converter = Mappers.getMapper(PhotoAlbumConverter::class.java)
        val photoAlbumDto = converter.convertToDto(photoAlbum)
        repository.save(photoAlbumDto)

        log.info("Фотоальбом создан.")
    }

    /**
     * Обновляем фотоальбом
     */
    @PutMapping("/photoAlbum/update/{id}")
    fun update(@PathVariable id: Long, @RequestBody photoAlbum : PhotoAlbumModel) : ResponseEntity<PhotoAlbumModel>
    {
        log.info("Обновляем фотоальбом.")

        val existingPhotoAlbum = repository.findById(id)
        if(existingPhotoAlbum != null) {
            var existingPhotoAlbumDto: PhotoAlbumDto = existingPhotoAlbum as PhotoAlbumDto
            var updatePhotoAlbumDto =  existingPhotoAlbumDto.copy(photoAlbumTitle = photoAlbum.photoAlbumTitle)

            val result = repository.save(updatePhotoAlbumDto)

            if (result != null) {
                val converter = Mappers.getMapper(PhotoAlbumConverter::class.java)
                val photoAlbumModel = converter.convertToModel(result)

                return ResponseEntity.ok(photoAlbumModel)
            }
        }

        return ResponseEntity.notFound().build()
    }

    /**
     * Удаляем фотоальбом
     */
    @DeleteMapping("/photoAlbum/delete/{id}")
    fun delete(@PathVariable id: Long)
    {
        log.info("Удаление фотоальбома.")

        repository.deleteById(id)

        log.info("Фотоальбом удалён.")
    }

}