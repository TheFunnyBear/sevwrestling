package com.wrestling.school.models

import java.util.*

/**
 * Видео
 */
data class VideoModel (
        /**
         * Идентификатор видео
         */
        val id: Long,

        /**
         * Уникальны идентификатор видео
         */
        val videoUuid: UUID,

        /**
         * Имя файла
         */
        val fileName: String,

        /**
         * Размер файла в байтах
         */
        val fileSize: Long,

        /**
         * Коментарий к файлу
         */
        val fileComment: String,

        /**
         * Файл удалён
         */
        val isDeleted: Boolean
)