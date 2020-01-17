package com.wrestling.school.repositories

import com.wrestling.school.dtos.CoachInfoDto
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface CoachInfoRepository : MongoRepository<CoachInfoDto, String> {
    fun findTopByOrderByIdDesc(): Optional<CoachInfoDto>
}