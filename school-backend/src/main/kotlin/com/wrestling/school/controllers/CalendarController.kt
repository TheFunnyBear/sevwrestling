package com.wrestling.school.controllers

import com.wrestling.school.dtos.CalendarDto
import com.wrestling.school.models.CalendarItem
import com.wrestling.school.models.CalendarModel
import com.wrestling.school.repositories.CalendarRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:8000"], maxAge = 4800, allowCredentials = "false")
class CalendarController (private val repository: CalendarRepository) {
    private val log = LoggerFactory.getLogger(Application::class.java)

    /**
     * Возвращает все календари
     */
    @GetMapping("/calendar")
    fun findAll(): List<CalendarModel> {

        return repository.findAll().map {
            calendarDto ->
            CalendarModel(
                    calendarDto.id,
                    CalendarItem(calendarDto.mondayIsWorkDay,
                            getTimeAsString(calendarDto.mondayOpenTime),
                            getTimeAsString(calendarDto.mondayCloseTime)),
                    CalendarItem(calendarDto.tuesdayIsWorkDay,
                            getTimeAsString(calendarDto.tuesdayOpenTime),
                            getTimeAsString(calendarDto.tuesdayCloseTime)),
                    CalendarItem(calendarDto.wednesdayIsWorkDay,
                            getTimeAsString(calendarDto.wednesdayOpenTime),
                            getTimeAsString(calendarDto.wednesdayCloseTime)),
                    CalendarItem(calendarDto.thursdayIsWorkDay,
                            getTimeAsString(calendarDto.thursdayOpenTime),
                            getTimeAsString(calendarDto.thursdayCloseTime)),
                    CalendarItem(calendarDto.fridayIsWorkDay,
                            getTimeAsString(calendarDto.fridayOpenTime),
                            getTimeAsString(calendarDto.fridayCloseTime)),
                    CalendarItem(calendarDto.saturdayIsWorkDay,
                            getTimeAsString(calendarDto.saturdayOpenTime),
                            getTimeAsString(calendarDto.sundayCloseTime)),
                    CalendarItem(calendarDto.sundayIsWorkDay,
                            getTimeAsString(calendarDto.sundayOpenTime),
                            getTimeAsString(calendarDto.sundayCloseTime)),
                    calendarDto.actualityDate)
        }
    }

    /**
     * Возвращает последний календарь
     */
    @GetMapping("/calendar/last")
    fun findLast(): ResponseEntity<CalendarModel>? {

        return repository.findTopByOrderByIdDesc().map {
            ResponseEntity.ok(CalendarModel(
                    it.id,
                    CalendarItem(it.mondayIsWorkDay,
                            getTimeAsString(it.mondayOpenTime),
                            getTimeAsString(it.mondayCloseTime)),
                    CalendarItem(it.tuesdayIsWorkDay,
                            getTimeAsString(it.tuesdayOpenTime),
                            getTimeAsString(it.tuesdayCloseTime)),
                    CalendarItem(it.wednesdayIsWorkDay,
                            getTimeAsString(it.wednesdayOpenTime),
                            getTimeAsString(it.wednesdayCloseTime)),
                    CalendarItem(it.thursdayIsWorkDay,
                            getTimeAsString(it.thursdayOpenTime),
                            getTimeAsString(it.thursdayCloseTime)),
                    CalendarItem(it.fridayIsWorkDay,
                            getTimeAsString(it.fridayOpenTime),
                            getTimeAsString(it.fridayCloseTime)),
                    CalendarItem(it.saturdayIsWorkDay,
                            getTimeAsString(it.saturdayOpenTime),
                            getTimeAsString(it.sundayCloseTime)),
                    CalendarItem(it.sundayIsWorkDay,
                            getTimeAsString(it.sundayOpenTime),
                            getTimeAsString(it.sundayCloseTime)),
                    it.actualityDate))
        }.orElseGet { ResponseEntity.notFound().build() }
    }

    /**
     * Создаёт новый календарь
     */
    @PostMapping("/calendar/create")
    fun create(@RequestBody calendarModel : CalendarModel) {
        log.info("Создание нового календаря.")

        val calendarDto = CalendarDto(
                UUID.randomUUID().toString(),
                calendarModel.monday.isWorkDay,

                parseTime(calendarModel.monday.openTime),
                parseTime(calendarModel.monday.closeTime),
                calendarModel.tuesday.isWorkDay,
                parseTime(calendarModel.tuesday.openTime),
                parseTime(calendarModel.tuesday.closeTime),
                calendarModel.wednesday.isWorkDay,
                parseTime(calendarModel.wednesday.openTime),
                parseTime(calendarModel.wednesday.closeTime),
                calendarModel.thursday.isWorkDay,
                parseTime(calendarModel.thursday.openTime),
                parseTime(calendarModel.thursday.closeTime),
                calendarModel.friday.isWorkDay,
                parseTime(calendarModel.friday.openTime),
                parseTime(calendarModel.friday.closeTime),
                calendarModel.saturday.isWorkDay,
                parseTime(calendarModel.saturday.openTime),
                parseTime(calendarModel.saturday.closeTime),
                calendarModel.sunday.isWorkDay,
                parseTime(calendarModel.sunday.openTime),
                parseTime(calendarModel.sunday.closeTime),
                calendarModel.actualityDate
        )

        repository.save(calendarDto)
        log.info("Календарь создан.")
    }

    /**
     * Удаляем календарь
     */
    @DeleteMapping("/calendar/delete/{id}")
    fun delete(@PathVariable id: String)
    {
        log.info("Удаление календаря.")

        repository.deleteById(id)

        log.info("Календарь удалён.")
    }

    /**
     * Преаброзует время в строку
     */
    private  fun getTimeAsString(time: LocalTime?) : String {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        return if (time == null) {
            ""
        }
        else {
            timeFormatter.format(time)
        }
    }

    /**
     * Парсинг времени
     */
    private fun parseTime(str: String?) : LocalTime? {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        return if (isNullOrEmpty(str)) {
            null
        }
        else {
            LocalTime.parse(str, timeFormatter)
        }
    }

    /**
     * Проверяем являетсял ли строка пустой
     */
    private fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && str.isNotEmpty())
            return false
        return true
    }
}