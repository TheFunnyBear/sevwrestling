package com.wrestling.school.repositories

import com.wrestling.school.dtos.MessageDto

interface MessagesRepository : JpaRepository<MessageDto, Long> {
    fun findByTitle(title: String): MessageDto?
}