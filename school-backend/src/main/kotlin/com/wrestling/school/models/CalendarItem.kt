package com.wrestling.school.models

class CalendarItem (
        /**
         * День является рабочим
         */
        val isWorkDay: Boolean,

        /**
         * Время начала тренеровки
         */
        val openTime: String,

        /**
         * Время окончания тренировки
         */
        val closeTime: String
)