package com.wrestling.school.controllers

import com.wrestling.school.dtos.VideoDto
import com.wrestling.school.models.VideoModel
import com.wrestling.school.repositories.VideoRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:8000"], maxAge = 4800, allowCredentials = "false")
class VideoController (private val repository: VideoRepository) {
    private val log = LoggerFactory.getLogger(Application::class.java)

    /**
     * Возвращает все видео
     */
    @GetMapping("/videos")
    fun findAll(): List<VideoModel> {
        return repository.findAll().map {
            videoDto ->
            VideoModel(
                    videoDto.videoUuid.toString(),
                    videoDto.uniqFileName,
                    videoDto.fileComment,
                    videoDto.isDeleted)
        }
    }

    /**
     * Возвращает пагинированный список видео с 1-м элементом для страницы
     */
    @GetMapping("/videos/{page}")
    fun getPage(@PathVariable page: Int): Page<VideoModel> {
        val currentPageWithTenMessages: Pageable = PageRequest.of(page, 1, Sort
                .by(Sort.Direction.DESC, "id"))

        return repository.findAll(currentPageWithTenMessages).map {
            videoDto ->
            VideoModel(
                    videoDto.videoUuid.toString(),
                    videoDto.uniqFileName,
                    videoDto.fileComment,
                    videoDto.isDeleted)
        }
    }

    /**
     * Создаёт новое видео
     */
    @PostMapping("/videos/create")
    fun create(@RequestBody videoModel : VideoModel) {
        log.info("Создание нового видео.")

        val videoDto = VideoDto(
                UUID.fromString(videoModel.videoUuid),
                videoModel.uniqFileName,
                videoModel.fileComment,
                videoModel.isDeleted
        )

        repository.save(videoDto)
        log.info("Видео созданно.")
    }

    /**
     * Удаляем видео
     */
    @DeleteMapping("/videos/delete/{id}")
    fun delete(@PathVariable id: String)
    {
        log.info("Удаление видео.")

        repository.deleteById(id)

        log.info("Видео удалено.")
    }
}