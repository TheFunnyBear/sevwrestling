package com.wrestling.school.dtos

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
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
         * Дата создания сообщения
         */
        @CreatedDate
        val createdDate: LocalDateTime = LocalDateTime.now(),

        /**
         * Дата публикации сообщения
         */
        val publicationDate: LocalDateTime = LocalDateTime.now(),

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

