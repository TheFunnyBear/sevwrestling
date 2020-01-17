package com.wrestling.school.dtos

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


/**
 * Информация о тренере
 */
@Document(collection = "CoachInfo")
data class CoachInfoDto (
        /**
         * Идентификатор тренера
         */
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        /**
         * Имя
         */
        val name: String = "",

        /**
         * Фамилия
         */
        val surname: String = "",

        /**
         * Отчество
         */
        val patronymic: String = "",

        /**
         * E-Mail
         */
        val email: String = "",

        /**
         * Телефон
         */
        val phone: String = "",

        /**
         * Viber
         */
        val viber: String = "",

        /**
         * Skype
         */
        val skype: String = ""
)