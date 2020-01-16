package com.wrestling.school.dtos

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Изображение
 */
@Entity
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

