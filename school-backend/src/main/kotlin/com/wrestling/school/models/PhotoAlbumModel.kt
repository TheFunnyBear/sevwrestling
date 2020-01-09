package com.wrestling.school.models

/**
 * Фотоальбом
 */
 class PhotoAlbumModel (
    /**
     * Идентификатор фотоальбома
     */
    val id: Long,

    /**
     * Заголовок фотоальбома
     */
    val photoAlbumTitle: String,

    /**
     * Описание фотоальбома
     */
    val photoAlbumDescription: String
)