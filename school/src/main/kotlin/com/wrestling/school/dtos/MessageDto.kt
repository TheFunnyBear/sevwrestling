package com.wrestling.school.dtos


import java.util.*
import javax.persistence.*
import org.springframework.data.annotation.Id

@Entity
data class MessageDto (
        @Id
        @GeneratedValue
        val id: Long
)

