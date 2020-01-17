package com.wrestling.school.repositories

import com.wrestling.school.dtos.PictureDto
import org.springframework.data.mongodb.repository.MongoRepository

interface PictureRepository : MongoRepository<PictureDto, Long> {
    fun findByPhotoAlbumId(photoAlbumId: Long): List<PictureDto>
}