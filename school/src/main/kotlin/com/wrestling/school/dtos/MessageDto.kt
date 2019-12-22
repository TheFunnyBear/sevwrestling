package com.wrestling.school.dtos

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Сообщение
 */
@Entity
data class MessageDto (

        /**
         * Идентификатор записи
         */
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        /**
         * Заголовок сообщения
         */
        val messageTitle: String = "",

        /**
         * Текст сообщения
         */
        val messageText: String = "",

        /**
         * Идентификатор изображения
         */
        val pictureUuid: UUID = UUID.randomUUID(),

        /**
         * Сообщение удалено
         */
        val isDeleted: Boolean = false
)

