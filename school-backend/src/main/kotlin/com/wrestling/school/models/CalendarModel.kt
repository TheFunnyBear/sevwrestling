package com.wrestling.school.models

import java.time.LocalDateTime
import java.util.*

class CalendarModel (
        /**
         * Идентификатор календаря
         */
        val id: String,

        /**
         * Понедельник
         */
        val monday: CalendarItem,

        /**
         * Вторник
         */
        val tuesday: CalendarItem,

        /**
         * Среда
         */
        val wednesday: CalendarItem,

        /**
         * Четверг
         */
        val thursday: CalendarItem,

        /**
         * Пятница
         */
        val friday: CalendarItem,

        /**
         * Суббота
         */
        val saturday: CalendarItem,

        /**
         * Воскресенье
         */
        val sunday: CalendarItem,

        /**
         * Дата актуальности
         */
        val actualityDate: LocalDateTime

)