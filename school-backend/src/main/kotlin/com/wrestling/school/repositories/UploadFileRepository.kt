package com.wrestling.school.repositories

import com.wrestling.school.dtos.UploadFileDto
import org.springframework.data.jpa.repository.Query
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface UploadFileRepository : MongoRepository<UploadFileDto, Long> {
    @Query("SELECT t FROM UploadFileDto t WHERE t.fileUuid = :uuid")
    fun findByFileUuid(uuid: UUID): List<UploadFileDto?>?
}