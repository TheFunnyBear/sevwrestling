package com.wrestling.school.models

import java.time.LocalDateTime
import java.util.*

/**
 * Сообщение
 */
class MessageModel (
        /**
         * Идентификатор записи
         */
        val id: Long,

        /**
         * Дата создания сообщения
         */
        val createdDate: LocalDateTime,

        /**
         * Дата публикации сообщения
         */
        val publicationDate: LocalDateTime,

        /**
         * Заголовок сообщения
         */
        val messageTitle: String,

        /**
         * Текст сообщения
         */
        val messageText: String,

        /**
         * Идентификатор изображения
         */
        val pictureUuid: UUID,

        /**
         * Сообщение удалено
         */
        val isDeleted: Boolean
)