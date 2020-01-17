package com.wrestling.school.models

import java.util.*

/**
 * Загруженный файл
 */
class UploadFileModel {
        constructor() {
                fileUuid = UUID.randomUUID()
                originalFileName = ""
                uniqFileName = ""
                fileDownloadUri = ""
                fileType = "application/octet-stream"
                size = 0
        }

        constructor(
                _fileUuid : UUID,
                _originalFileName : String,
                _uniqFileName : String,
                _fileDownloadUri : String,
                _fileType : String,
                _size : Long
                )
        {
                fileUuid = _fileUuid
                originalFileName = _originalFileName
                uniqFileName = _uniqFileName
                fileDownloadUri = _fileDownloadUri
                fileType = _fileType
                size = _size
        }

        /**
         * Идентификатор файла
         */
        var fileUuid: UUID

        /**
         *  Имя файла загруженного пользователем
         */
        val originalFileName: String

        /**
         *  Уникальное имя файла
         */
        val uniqFileName: String

        /**
         * Строка для загрузки
         */
        val fileDownloadUri: String

        /**
         * Тип файла
         */
        val fileType: String

        /**
         * Размер фала
         */
        val size: Long;
}