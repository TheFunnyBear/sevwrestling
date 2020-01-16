package com.wrestling.school.repositories

import com.wrestling.school.dtos.VideoDto
import org.springframework.data.repository.PagingAndSortingRepository

interface VideoRepository : PagingAndSortingRepository<VideoDto, Long> {

}