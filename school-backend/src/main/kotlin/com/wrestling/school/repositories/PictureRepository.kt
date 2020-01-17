package com.wrestling.school.repositories

import com.wrestling.school.dtos.PictureDto
import org.springframework.data.mongodb.repository.MongoRepository

interface PictureRepository : MongoRepository<PictureDto, String> {
    fun findByPhotoAlbumId(photoAlbumId: String): List<PictureDto>
}