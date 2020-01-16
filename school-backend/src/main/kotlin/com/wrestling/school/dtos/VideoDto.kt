package com.wrestling.school.dtos

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Видео файл
 */
@Entity
data class VideoDto (
        /**
         * Идентификатор видео
         */
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        /**
         * Уникальны идентификатор видео
         */
        val videoUuid: UUID = UUID.randomUUID(),

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