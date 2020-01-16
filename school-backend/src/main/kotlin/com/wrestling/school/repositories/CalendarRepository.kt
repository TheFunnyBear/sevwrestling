package com.wrestling.school.repositories

import com.wrestling.school.dtos.CalendarDto
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface CalendarRepository : PagingAndSortingRepository<CalendarDto, Long> {
    fun findTopByOrderByIdDesc(): Optional<CalendarDto>
}