package com.wrestling.school.dtos

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Расписание
 */
@Document(collection = "Calendar")
data class CalendarDto (
        /**
         * Идентификатор календаря
         */
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        /**
         *  Понедельник: День является рабочим
         */
        val mondayIsWorkDay: Boolean = false,

        /**
         * Понедельник: Время начала тренеровки
         */
        val mondayOpenTime: LocalTime? = null,

        /**
         * Понедельник: Время окончания тренировки
         */
        val mondayCloseTime: LocalTime? = null,

        /**
         *  Вторник: День является рабочим
         */
        val tuesdayIsWorkDay: Boolean = false,

        /**
         * Вторник: Время начала тренеровки
         */
        val tuesdayOpenTime: LocalTime? = null,

        /**
         * Вторник: Время окончания тренировки
         */
        val tuesdayCloseTime: LocalTime? = null,

        /**
         *  Среда: День является рабочим
         */
        val wednesdayIsWorkDay: Boolean = false,

        /**
         * Среда: Время начала тренеровки
         */
        val wednesdayOpenTime: LocalTime? = null,

        /**
         * Среда: Время окончания тренировки
         */
        val wednesdayCloseTime: LocalTime? = null,

        /**
         *  Четверг: День является рабочим
         */
        val thursdayIsWorkDay: Boolean = false,

        /**
         * Четверг: Время начала тренеровки
         */
        val thursdayOpenTime: LocalTime? = null,

        /**
         * Четверг: Время окончания тренировки
         */
        val thursdayCloseTime: LocalTime? = null,

        /**
         *  Пятница: День является рабочим
         */
        val fridayIsWorkDay: Boolean = false,

        /**
         * Пятница: Время начала тренеровки
         */
        val fridayOpenTime: LocalTime? = null,

        /**
         * Пятница: Время окончания тренировки
         */
        val fridayCloseTime: LocalTime? = null,

        /**
         *  Суббота: День является рабочим
         */
        val saturdayIsWorkDay: Boolean = false,

        /**
         * Суббота: Время начала тренеровки
         */
        val saturdayOpenTime: LocalTime? = null,

        /**
         * Суббота: Время окончания тренировки
         */
        val saturdayCloseTime: LocalTime? = null,

        /**
         *  Воскресенье: День является рабочим
         */
        val sundayIsWorkDay: Boolean = false,

        /**
         * Воскресенье: Время начала тренеровки
         */
        val sundayOpenTime: LocalTime? = null,

        /**
         * Воскресенье: Время окончания тренировки
         */
        val sundayCloseTime: LocalTime? = null,

        /**
         * Дата актуальности
         */
        val actualityDate: LocalDateTime = LocalDateTime.now()
)