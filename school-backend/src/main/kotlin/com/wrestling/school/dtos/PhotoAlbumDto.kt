package com.wrestling.school.dtos

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
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
        val id: String = UUID.randomUUID().toString(),

        /**
         * Заголовок фотоальбома
         */
        val photoAlbumTitle: String = "",

        /**
         * Описание фотоальбома
         */
        val photoAlbumDescription: String = ""
)