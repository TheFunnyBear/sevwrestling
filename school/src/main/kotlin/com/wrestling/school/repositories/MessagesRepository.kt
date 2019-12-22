package com.wrestling.school.repositories

import com.wrestling.school.dtos.MessageDto
import org.springframework.data.repository.CrudRepository

interface MessagesRepository : CrudRepository<MessageDto, Long> {

    //override fun findAll(): MutableIterable<MessageDto>
    //override fun findById(@PathVariable id: Long): Optional<MessageDto>
}