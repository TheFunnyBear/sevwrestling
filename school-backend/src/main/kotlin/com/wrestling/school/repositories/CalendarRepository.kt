package com.wrestling.school.repositories

import com.wrestling.school.dtos.CalendarDto
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface CalendarRepository : MongoRepository<CalendarDto, String> {
    fun findTopByOrderByIdDesc(): Optional<CalendarDto>
}