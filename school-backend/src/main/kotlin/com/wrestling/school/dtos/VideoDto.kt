package com.wrestling.school.dtos

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Видео файл
 */
@Document(collection = "Videos")
data class VideoDto (
        /**
         * Уникальны идентификатор видео
         */
        @Id
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