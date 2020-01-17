package com.wrestling.school.repositories

import com.wrestling.school.dtos.CalendarDto
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface CalendarRepository : MongoRepository<CalendarDto, Long> {
    fun findTopByOrderByIdDesc(): Optional<CalendarDto>
}