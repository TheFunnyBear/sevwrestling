package com.wrestling.school.resources

import java.util.*

class MessageResource (
        // Идентификатор записи
        val id: Long,

        // Заголовок сообщения
        val messageTitle: String,

        // Текст сообщения
        val messageText: String,

        // Идентификатор изображения
        val pictureUuid: UUID,

        // Сообщение удалено
        val isDeleted: Boolean
)