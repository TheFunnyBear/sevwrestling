package com.wrestling.school.models

import org.springframework.web.servlet.support.ServletUriComponentsBuilder

/**
 * Видео
 */
class VideoModel (
        /**
         * Уникальны идентификатор видео
         */
        val videoUuid: String,

        /**
         *  Уникальное имя файла
         */
        val uniqFileName: String,

        /**
         * Коментарий к файлу
         */
        val fileComment: String,

        /**
         * Файл удалён
         */
        val isDeleted: Boolean
) {
        /**
         * Строка для загрузки
         */
        val videoDownloadUri: String
        get() {
                return ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/files/downloadFile/")
                        .path(uniqFileName)
                        .toUriString()
        }

}
