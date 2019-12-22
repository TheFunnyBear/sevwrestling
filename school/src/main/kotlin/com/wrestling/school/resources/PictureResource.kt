package com.wrestling.school.resources

import java.util.*

data class PictureResource (
        // Идентификатор изображения
        val id: Long,

        // Уникальны идентификатор изображения
        val pictureUuid: UUID,

        // Имя файла
        val fileName: String,

        // Размер файла в байтах
        val fileSize: Long,

        // Коментарий к файлу
        val fileComment: String,

        // Файл удалён
        val isDeleted: Boolean
)