package com.wrestling.school.models

import org.springframework.web.servlet.support.ServletUriComponentsBuilder

/**
 * Изображение
 */
class PictureModel (
        /**
         * Идентификатор изображения
         */
        val id: Long,

        /**
         * Уникальны идентификатор изображения
         */
        val pictureUuid: String,

        /**
         * Идентификатор фотоальбома
         */
        val photoAlbumId: Long,

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
        val pictureDownloadUri: String
                get() {
                        return ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/api/files/downloadFile/")
                                .path(uniqFileName)
                                .toUriString()
                }

}