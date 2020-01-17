package com.wrestling.school.dtos

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
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
        val id: String = UUID.randomUUID().toString(),

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