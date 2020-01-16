package com.wrestling.school.controllers

import com.wrestling.school.dtos.PhotoAlbumDto
import com.wrestling.school.models.PhotoAlbumModel
import com.wrestling.school.repositories.PhotoAlbumRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:8000"], maxAge = 4800, allowCredentials = "false")
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
        val currentPageWithTenMessages: Pageable = PageRequest.of(page, 10, Sort
                .by(Sort.Direction.DESC, "id"))

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

        //val converter = Mappers.getMapper(PhotoAlbumConverter::class.java)
        //val photoAlbumDto = converter.convertToDto(photoAlbum)

        val photoAlbumDto = PhotoAlbumDto(
                photoAlbum.id,
                photoAlbum.photoAlbumTitle,
                photoAlbum.photoAlbumDescription
        )

        repository.save(photoAlbumDto)
        log.info("Фотоальбом создан.")
    }

    /**
     * Обновляем фотоальбом
     */
    @PostMapping("/photoAlbum/update/{id}")
    fun update(@PathVariable id: Long, @RequestBody photoAlbum : PhotoAlbumModel) : ResponseEntity<PhotoAlbumModel>
    {
        log.info("Обновляем фотоальбом.")

        val existingPhotoAlbum = repository.findById(id)
        if(existingPhotoAlbum != null) {
            var existingPhotoAlbumDto: PhotoAlbumDto = existingPhotoAlbum.get()


            var updatePhotoAlbumDto =  existingPhotoAlbumDto.copy(
                    photoAlbumTitle = photoAlbum.photoAlbumTitle,
                    photoAlbumDescription =photoAlbum.photoAlbumDescription
            )

            val result = repository.save(updatePhotoAlbumDto)

            if (result != null) {
                //val converter = Mappers.getMapper(PhotoAlbumConverter::class.java)
                //val photoAlbumModel = converter.convertToModel(result)


                return ResponseEntity.ok(PhotoAlbumModel(
                        result.id,
                        result.photoAlbumTitle,
                        result.photoAlbumDescription
                ))
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