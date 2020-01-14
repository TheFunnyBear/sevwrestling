package com.wrestling.school.repositories

import com.wrestling.school.dtos.PictureDto
import com.wrestling.school.dtos.UploadFileDto
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface PictureRepository : PagingAndSortingRepository<PictureDto, Long> {
    fun findByPhotoAlbumId(photoAlbumId: Long): List<PictureDto>
}