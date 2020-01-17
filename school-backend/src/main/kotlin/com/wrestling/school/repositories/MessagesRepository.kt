package com.wrestling.school.repositories

import com.wrestling.school.dtos.MessageDto
import org.springframework.data.mongodb.repository.MongoRepository


interface MessagesRepository : MongoRepository<MessageDto, Long> {

}