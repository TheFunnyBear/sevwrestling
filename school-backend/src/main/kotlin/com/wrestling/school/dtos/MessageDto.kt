package com.wrestling.school.dtos

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Сообщение
 */
@Document(collection = "Messages")
data class MessageDto (
        /**
         * Идентификатор записи
         */
        @Id
        val id: String = UUID.randomUUID().toString(),

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
         *  Уникальное имя файла изображения
         */
        val uniqFileName: String = "",

        /**
         * Сообщение удалено
         */
        val isDeleted: Boolean = false
)

