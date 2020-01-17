package com.wrestling.school.models

import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime
import java.util.*

/**
 * Сообщение
 */
class MessageModel (
        /**
         * Идентификатор записи
         */
        val id: String,

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
         *  Уникальное имя файла
         */
        val uniqFileName: String,

        /**
         * Сообщение удалено
         */
        val isDeleted: Boolean
) {
        /**
         * Строка для загрузки
         */
        val pictureDownloadUri: String
                get() {
                        return ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/api/files/downloadFile/")
                                .path(uniqFileName)
                                .toUriString()
                }
}