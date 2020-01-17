package com.wrestling.school.controllers

import com.wrestling.school.dtos.MessageDto
import com.wrestling.school.models.MessageModel
import com.wrestling.school.repositories.MessagesRepository
import com.wrestling.school.repositories.PictureRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:8000"], maxAge = 4800, allowCredentials = "false")
class MessagesController (
        private val repository: MessagesRepository,
        private val pictureRepository: PictureRepository){
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
                formFileNameIfNotExist(messageDto.uniqFileName, messageDto.pictureUuid),
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
                .by(Sort.Direction.DESC, "id"))
        return repository.findAll(currentPageWithTenMessages).map {
            messageDto -> MessageModel(
                messageDto.id,
                messageDto.createdDate,
                messageDto.publicationDate,
                messageDto.messageTitle,
                messageDto.messageText,
                messageDto.pictureUuid,
                formFileNameIfNotExist(messageDto.uniqFileName, messageDto.pictureUuid),
                messageDto.isDeleted)
        }
    }

    /**
     * Возвращает сообщение по его идентификатору
     */
    @GetMapping("/message/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<MessageModel>? {
        return repository.findById(id).map {
            ResponseEntity.ok(MessageModel(
                    it.id,
                    it.createdDate,
                    it.publicationDate,
                    it.messageTitle,
                    it.messageText,
                    it.pictureUuid,
                    formFileNameIfNotExist(it.uniqFileName, it.pictureUuid),
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

        val messageDto = MessageDto(
                UUID.randomUUID().toString(),
                message.createdDate,
                message.publicationDate,
                message.messageTitle,
                message.messageText,
                message.pictureUuid,
                formFileNameIfNotExist(message.uniqFileName, message.pictureUuid),
                message.isDeleted
        )

        repository.save(messageDto)

        log.info("Создание созданно.")
    }

    /**
     * Обновляем сообщение
     */
    @PostMapping("/message/update/{id}")
    fun update(@PathVariable id: String, @RequestBody message :MessageModel) : ResponseEntity<MessageModel>
    {
        val existingMessage = repository.findById(id)
        val existingMessageDto: MessageDto = existingMessage.get()
        val updateMessageDto =  existingMessageDto.copy(
                publicationDate = message.publicationDate,
                messageTitle = message.messageTitle,
                messageText = message.messageText
        )

        val result = repository.save(updateMessageDto)

        return ResponseEntity.ok(MessageModel(
                result.id,
                result.createdDate,
                result.publicationDate,
                result.messageTitle,
                result.messageText,
                result.pictureUuid,
                formFileNameIfNotExist(result.uniqFileName, result.pictureUuid),
                result.isDeleted
        ))
    }

    /**
     * Удаляем сообщение
     */
    @DeleteMapping("/message/delete/{id}")
    fun delete(@PathVariable id: String)
    {
        log.info("Удаление сообщения.")

        repository.deleteById(id)

        log.info("Сообщение удалено.")
    }

    /**
     * Получает имя файла из репозитория с файлами если его нет
     */
    private fun formFileNameIfNotExist(uniqFileName: String, pictureUuid : UUID) : String
    {
        if(isNullOrEmpty(uniqFileName)) {
            val pictureInRepository = pictureRepository.findById(pictureUuid.toString())
            if (pictureInRepository.isPresent) {
                val picture = pictureInRepository.get()
                return picture.uniqFileName
            }

            return ""

        }
        else {
            return uniqFileName
        }
    }

    /**
     * Проверяем являетсял ли строка пустой
     */
    private fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && str.isNotEmpty())
            return false
        return true
    }
}