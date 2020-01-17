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
         * Идентификатор изображения
         */
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        /**
         * Уникальны идентификатор изображения
         */
        val pictureUuid: UUID = UUID.randomUUID(),

        /**
         * Идентификатор фотоальбома
         */
        val photoAlbumId: Long = 0,

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

