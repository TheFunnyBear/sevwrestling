package com.wrestling.school.payload

import java.util.*

class UploadFileResponse(
        /**
         * Идентификатор файла
         */
        val fileUuid: UUID,

        /**
         * Имя файла
         */
        val fileName: String,

        /**
         * Строка для загрузки
         */
        val fileDownloadUri: String,

        /**
         * Тип файла
         */
        val fileType: String,

        /**
         * Размер фала
         */
        val size: Long)