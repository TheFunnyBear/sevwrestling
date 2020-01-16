package com.wrestling.school.repositories

import com.wrestling.school.dtos.PictureDto
import org.springframework.data.repository.PagingAndSortingRepository

interface PictureRepository : PagingAndSortingRepository<PictureDto, Long> {
    fun findByPhotoAlbumId(photoAlbumId: Long): List<PictureDto>
}