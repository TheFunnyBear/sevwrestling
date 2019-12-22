package com.wrestling.school.models

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