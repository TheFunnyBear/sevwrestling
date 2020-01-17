package com.wrestling.school.controllers

import com.wrestling.school.dtos.PictureDto
import com.wrestling.school.models.PictureModel
import com.wrestling.school.repositories.PictureRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:8000"], maxAge = 4800, allowCredentials = "false")
class PictureController (private val repository: PictureRepository) {
    private val log = LoggerFactory.getLogger(Application::class.java)

    /**
     * Возвращает все фотографии
     */
    @GetMapping("/pictures")
    fun findAll(): List<PictureModel> {
        return repository.findAll().map {
            pictureDto ->
            PictureModel(
                    pictureDto.pictureUuid.toString(),
                    pictureDto.photoAlbumId,
                    pictureDto.uniqFileName,
                    pictureDto.fileComment,
                    pictureDto.isDeleted)
        }
    }

    /**
     * Возвращает фотографию по её идентификатору
     */
    @GetMapping("/picture/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<PictureModel>? {

        return repository.findById(id).map {
            ResponseEntity.ok(PictureModel(
                    it.pictureUuid.toString(),
                    it.photoAlbumId,
                    it.uniqFileName,
                    it.fileComment,
                    it.isDeleted
            ))
        }.orElseGet { ResponseEntity.notFound().build() }
    }

    /**
     * Возвращает пагинированный список фотографий с 10-ю элементами для страницы
     */
    @GetMapping("/pictures/{page}")
    fun getPage(@PathVariable page: Int): Page<PictureModel> {
        val currentPageWithTenMessages: Pageable = PageRequest.of(page, 10, Sort
                .by(Sort.Direction.DESC, "id"))

        return repository.findAll(currentPageWithTenMessages).map {
            pictureDto ->
            PictureModel(
                    pictureDto.pictureUuid.toString(),
                    pictureDto.photoAlbumId,
                    pictureDto.uniqFileName,
                    pictureDto.fileComment,
                    pictureDto.isDeleted)
        }
    }

    /**
     * Возвращает все фотографии фотоальбома по его идентификатору
     */
    @GetMapping("/photoAlbumPictures/{id}")
    fun findAllInPhotoAlbumId(@PathVariable id: Long): List<PictureModel> {

        return repository.findByPhotoAlbumId(id).map { pictureDto ->
            PictureModel(
                    pictureDto.pictureUuid.toString(),
                    pictureDto.photoAlbumId,
                    pictureDto.uniqFileName,
                    pictureDto.fileComment,
                    pictureDto.isDeleted)
        }
    }

    /**
     * Создаёт новую фотографию
     */
    @PostMapping("/pictures/create")
    fun create(@RequestBody pictureModel : PictureModel) {
        log.info("Создание новой фотографии.")

        val photoAlbumDto = PictureDto(
                UUID.fromString(pictureModel.pictureUuid),
                pictureModel.photoAlbumId,
                pictureModel.uniqFileName,
                pictureModel.fileComment,
                pictureModel.isDeleted
        )

        repository.save(photoAlbumDto)
        log.info("Фотография создана.")
    }

    /**
     * Обновляем фотоальбом
     */
    @PostMapping("/pictures/update/{id}")
    fun update(@PathVariable id: String, @RequestBody pictureModel : PictureModel) : ResponseEntity<PictureModel>
    {
        log.info("Обновляем фотографию.")

        val existingPhoto = repository.findById(id)
        val existingPictureDto: PictureDto = existingPhoto.get()

        val updatePictureDto =  existingPictureDto.copy(
                pictureUuid = UUID.fromString(pictureModel.pictureUuid),
                photoAlbumId = pictureModel.photoAlbumId,
                uniqFileName = pictureModel.uniqFileName,
                fileComment = pictureModel.fileComment,
                isDeleted = pictureModel.isDeleted
        )

        val result = repository.save(updatePictureDto)

        return ResponseEntity.ok(PictureModel(
                result.pictureUuid.toString(),
                result.photoAlbumId,
                result.uniqFileName,
                result.fileComment,
                result.isDeleted
        ))
    }

    /**
     * Удаляем фотографию
     */
    @DeleteMapping("/pictures/delete/{id}")
    fun delete(@PathVariable id: String)
    {
        log.info("Удаление фотоальбома.")

        repository.deleteById(id)

        log.info("Фотоальбом удалён.")
    }
}