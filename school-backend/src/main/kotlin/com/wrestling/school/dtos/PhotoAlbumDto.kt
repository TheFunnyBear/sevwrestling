package com.wrestling.school.dtos

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Фотоальбом
 */
@Document(collection = "PhotoAlbums")
data class PhotoAlbumDto (
        /**
         * Идентификатор фотоальбома
         */
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        /**
         * Заголовок фотоальбома
         */
        val photoAlbumTitle: String = "",

        /**
         * Описание фотоальбома
         */
        val photoAlbumDescription: String = ""
)