package com.wrestling.school.dtos

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Загруженный файл
 */
@Entity
data class UploadFileDto (
        /**
         * Идентификатор
         */
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        /**
         *  Уникальный идентификатор файла
         */
        val fileUuid: UUID = UUID.randomUUID(),

        /**
         *  Имя файла загруженного пользователем
         */
        val  originalFileName: String = "",

        /**
         *  Уникальное имя файла
         */
        val uniqFileName: String = "",

        /**
         * Строка для загрузки
         */
        val fileDownloadUri: String = "",

        /**
         * Тип файла
         */
        val fileType: String = "",

        /**
         * Размер фала
         */
        val size: Long = 0
)