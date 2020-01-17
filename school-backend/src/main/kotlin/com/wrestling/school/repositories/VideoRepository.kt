package com.wrestling.school.repositories

import com.wrestling.school.dtos.VideoDto
import org.springframework.data.mongodb.repository.MongoRepository

interface VideoRepository : MongoRepository<VideoDto, Long> {

}