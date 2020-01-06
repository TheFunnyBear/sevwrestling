package com.wrestling.school.dtos

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 *
 */
@Entity
data class VideoResource (
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
         * Имя файла
         */
        val fileName: String = "",

        /**
         * Размер файла в байтах
         */
        val fileSize: Long = 0,

        /**
         * Коментарий к файлу
         */
        val fileComment: String = "",

        /**
         * Файл удалён
         */
        val isDeleted: Boolean = false
)