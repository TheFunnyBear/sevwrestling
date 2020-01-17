package com.wrestling.school.repositories

import com.wrestling.school.dtos.PhotoAlbumDto
import org.springframework.data.mongodb.repository.MongoRepository

interface PhotoAlbumRepository : MongoRepository<PhotoAlbumDto, String> {

}