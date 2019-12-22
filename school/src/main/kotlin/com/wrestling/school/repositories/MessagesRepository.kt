package com.wrestling.school.repositories

import com.wrestling.school.dtos.MessageDto
import com.wrestling.school.resources.MessageResource
import org.springframework.data.repository.CrudRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

interface MessagesRepository : CrudRepository<MessageDto, Long> {

    //override fun findAll(): MutableIterable<MessageDto>
    //override fun findById(@PathVariable id: Long): Optional<MessageDto>
}