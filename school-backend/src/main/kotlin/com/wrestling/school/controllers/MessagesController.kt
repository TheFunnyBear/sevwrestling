package com.wrestling.school.controllers

import com.wrestling.school.dtos.MessageDto
import com.wrestling.school.models.MessageModel
import com.wrestling.school.repositories.MessagesRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:8000"], maxAge = 4800, allowCredentials = "false")
class MessagesController (private val repository: MessagesRepository){
    private val log = LoggerFactory.getLogger(Application::class.java)

    /**
     * Возвращает все сообщения
     */
    @GetMapping("/messages")
    fun findAll(): List<MessageModel> {
        return repository.findAll().map {
            messageDto -> MessageModel(
                messageDto.id,
                messageDto.createdDate,
                messageDto.publicationDate,
                messageDto.messageTitle,
                messageDto.messageText,
                messageDto.pictureUuid,
                messageDto.isDeleted)
        }
    }

    /**
     * Возвращает пагинированный список с 10-ю элементами для страницы
     * Сортировка по убыванию идентификатора
     */
    @GetMapping("/messages/{page}")
    fun getPage(@PathVariable page: Int): Page<MessageModel> {
        val currentPageWithTenMessages: Pageable = PageRequest.of(page, 10, Sort
                .by(Sort.Direction.DESC, "id"));
        return repository.findAll(currentPageWithTenMessages).map {
            messageDto -> MessageModel(
                messageDto.id,
                messageDto.createdDate,
                messageDto.publicationDate,
                messageDto.messageTitle,
                messageDto.messageText,
                messageDto.pictureUuid,
                messageDto.isDeleted)
        }
    }

    /**
     * Возвращает сообщение по его идентификатору
     */
    @GetMapping("/message/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<MessageModel>? {
        return repository.findById(id).map {
            ResponseEntity.ok(MessageModel(
                    it.id,
                    it.createdDate,
                    it.publicationDate,
                    it.messageTitle,
                    it.messageText,
                    it.pictureUuid,
                    it.isDeleted
            ))
        }.orElseGet { ResponseEntity.notFound().build() }
    }

    /**
     * Создаёт новое сообщение
     */
    @PostMapping("/message/create")
    fun create(@RequestBody message :MessageModel) {
        log.info("Создание нового сообщения.")

        //val converter = Mappers.getMapper(MessageConverter::class.java) // or PersonConverterImpl()
        val messageDto = MessageDto(
                message.id,
                message.createdDate,
                message.publicationDate,
                message.messageTitle,
                message.messageText,
                message.pictureUuid,
                message.isDeleted
        );


        //val messageDto = converter.convertToDto(message);
        repository.save(messageDto);

        log.info("Создание созданно.")
    }

    /**
     * Обновляем сообщение
     */
    @PostMapping("/message/update/{id}")
    fun update(@PathVariable id: Long, @RequestBody message :MessageModel) : ResponseEntity<MessageModel>
    {
        val existingMessage = repository.findById(id);
        if(existingMessage != null) {
            var existingMessageDto: MessageDto = existingMessage.get()
            var updateMessageDto =  existingMessageDto.copy(
                    publicationDate = message.publicationDate,
                    messageTitle = message.messageTitle,
                    messageText = message.messageText
            )

            val result = repository.save(updateMessageDto);

            if (result != null) {
                //val converter = Mappers.getMapper(MessageConverter::class.java)
                //val messageModel = converter.convertToModel(result);

                return ResponseEntity.ok(MessageModel(
                        result.id,
                        result.createdDate,
                        result.publicationDate,
                        result.messageTitle,
                        result.messageText,
                        result.pictureUuid,
                        result.isDeleted
                ));
            }
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Удаляем сообщение
     */
    @DeleteMapping("/message/delete/{id}")
    fun delete(@PathVariable id: Long)
    {
        log.info("Удаление сообщения.")

        repository.deleteById(id);

        log.info("Сообщение удалено.")
    }
}