package com.wrestling.school.controllers

import com.wrestling.school.repositories.MessagesRepository
import com.wrestling.school.resources.MessageResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MessagesController (private val repository: MessagesRepository){

    /**
     * Возвращает все сообщения
     */
    @GetMapping("/messages")
    fun findAll(): List<MessageResource> {
        return repository.findAll().map {
            messageDto -> MessageResource(
                messageDto.id,
                messageDto.messageTitle,
                messageDto.messageText,
                messageDto.pictureUuid,
                messageDto.isDeleted
                )
        }
    }

    /**
     * Возвращает сообщение по его идентификатору
     */
    @GetMapping("/message/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<MessageResource>? {
        return repository.findById(id).map {
            ResponseEntity.ok(MessageResource(
                    it.id,
                    it.messageTitle,
                    it.messageText,
                    it.pictureUuid,
                    it.isDeleted
            ))
        }.orElseGet { ResponseEntity.notFound().build() }
    }


    @GetMapping("/greeting")
    fun greeting() : String
    {
        return "Hi!!!";
    }
}