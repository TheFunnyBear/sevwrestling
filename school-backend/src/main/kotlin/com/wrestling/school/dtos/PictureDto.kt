package com.wrestling.school.dtos

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Изображение
 */
@Document(collection = "Pictures")
data class PictureDto (
        /**
         * Уникальны идентификатор изображения
         */
        @Id
        val pictureUuid: UUID = UUID.randomUUID(),

        /**
         * Идентификатор фотоальбома
         */
        val photoAlbumId: String = "",

        /**
         *  Уникальное имя файла
         */
        val uniqFileName: String = "",

        /**
         * Коментарий к файлу
         */
        val fileComment: String = "",

        /**
         * Файл удалён
         */
        val isDeleted: Boolean = false
)

