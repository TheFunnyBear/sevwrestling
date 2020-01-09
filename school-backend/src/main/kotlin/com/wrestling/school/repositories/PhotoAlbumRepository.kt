package com.wrestling.school.repositories

import com.wrestling.school.dtos.PhotoAlbumDto
import org.springframework.data.repository.PagingAndSortingRepository

interface PhotoAlbumRepository : PagingAndSortingRepository<PhotoAlbumDto, Long> {

}