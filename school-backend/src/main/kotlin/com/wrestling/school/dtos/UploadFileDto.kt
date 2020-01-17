package com.wrestling.school.dtos

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Загруженный файл
 */
@Document(collection = "UploadFiles")
data class UploadFileDto (
        /**
         * Идентификатор
         */
        @Id
        val id: String = UUID.randomUUID().toString(),

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