package com.wrestling.school.controllers

import com.wrestling.school.dtos.CoachInfoDto
import com.wrestling.school.models.CoachInfoModel
import com.wrestling.school.repositories.CoachInfoRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:8000"], maxAge = 4800, allowCredentials = "false")
class CoachInfoController (private val repository: CoachInfoRepository) {
    private val log = LoggerFactory.getLogger(Application::class.java)

    /**
     * Возвращает всех тренеров
     */
    @GetMapping("/coach")
    fun findAll(): List<CoachInfoModel> {

        return repository.findAll().map { coachInfoDto ->
            CoachInfoModel(
                    coachInfoDto.id,
                    coachInfoDto.name,
                    coachInfoDto.surname,
                    coachInfoDto.patronymic,
                    coachInfoDto.email,
                    coachInfoDto.phone,
                    coachInfoDto.viber,
                    coachInfoDto.skype)
        }
    }

    /**
     * Возвращает последнего тренера
     */
    @GetMapping("/coach/last")
    fun findLast(): ResponseEntity<CoachInfoModel>? {

        return repository.findTopByOrderByIdDesc().map {
            ResponseEntity.ok(CoachInfoModel(
                    it.id,
                    it.name,
                    it.surname,
                    it.patronymic,
                    it.email,
                    it.phone,
                    it.viber,
                    it.skype))
        }.orElseGet { ResponseEntity.notFound().build() }
    }

    /**
     * Создаёт нового тренера
     */
    @PostMapping("/coach/create")
    fun create(@RequestBody coachInfoModel: CoachInfoModel) {
        log.info("Создание нового тренера.")

        val calendarDto = CoachInfoDto(
                UUID.randomUUID().toString(),
                coachInfoModel.name,
                coachInfoModel.surname,
                coachInfoModel.patronymic,
                coachInfoModel.email,
                coachInfoModel.phone,
                coachInfoModel.viber,
                coachInfoModel.skype
        )

        repository.save(calendarDto)
        log.info("Тренер создан.")
    }
}