package com.wrestling.school.controllers

import antlr.build.ANTLR.root
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.persistence.criteria.Predicate

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
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
                    pictureDto.id,
                    pictureDto.pictureUuid.toString(),
                    pictureDto.photoAlbumId,
                    pictureDto.uniqFileName,
                    pictureDto.fileComment,
                    pictureDto.isDeleted)
        }
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
                    pictureDto.id,
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

        return repository.findByPhotoAlbumId(id)?.map {
            pictureDto ->
            PictureModel(
                    pictureDto.id,
                    pictureDto.pictureUuid.toString(),
                    pictureDto.photoAlbumId,
                    pictureDto.uniqFileName,
                    pictureDto.fileComment,
                    pictureDto.isDeleted)
        }!!
    }

    /**
     * Создаёт новую фотографию
     */
    @PostMapping("/pictures/create")
    fun create(@RequestBody pictureModel : PictureModel) {
        log.info("Создание новой фотографии.")

        //val converter = Mappers.getMapper(PhotoAlbumConverter::class.java)
        //val photoAlbumDto = converter.convertToDto(photoAlbum)

        val photoAlbumDto = PictureDto(
                pictureModel.id,
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
    @PutMapping("/pictures/update/{id}")
    fun update(@PathVariable id: Long, @RequestBody pictureModel : PictureModel) : ResponseEntity<PictureModel>
    {
        log.info("Обновляем фотографию.")

        val existingPhoto = repository.findById(id)
        var existingPictureDto: PictureDto = existingPhoto.get()

        var updatePictureDto =  existingPictureDto.copy(
                pictureUuid = UUID.fromString(pictureModel.pictureUuid),
                photoAlbumId = pictureModel.photoAlbumId,
                fileComment = pictureModel.fileComment,
                isDeleted = pictureModel.isDeleted
        )

        val result = repository.save(updatePictureDto)


        return ResponseEntity.ok(PictureModel(
                result.id,
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
    fun delete(@PathVariable id: Long)
    {
        log.info("Удаление фотоальбома.")

        repository.deleteById(id)

        log.info("Фотоальбом удалён.")
    }

}