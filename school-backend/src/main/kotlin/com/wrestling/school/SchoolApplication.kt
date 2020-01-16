package com.wrestling.school

import com.wrestling.school.dtos.MessageDto
import com.wrestling.school.dtos.PhotoAlbumDto
import com.wrestling.school.dtos.PictureDto
import com.wrestling.school.repositories.MessagesRepository
import com.wrestling.school.repositories.PhotoAlbumRepository
import com.wrestling.school.repositories.PictureRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime
import java.util.*

@SpringBootApplication
class SchoolApplication {
    private val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    fun init(messagesRepository: MessagesRepository,
             photoAlbumRepository: PhotoAlbumRepository,
             pictureRepository: PictureRepository ) = CommandLineRunner {

        val currentDate = LocalDateTime.now()
        for(i in 1..100)
        {
            messagesRepository.save(MessageDto(i.toLong(), currentDate, currentDate, "Test message $i", "Text for message $i"));
        }

        for(i in 1..3) {
            val savedDto = photoAlbumRepository.save(PhotoAlbumDto(i.toLong(), "Тестовый фотоальбом $i", "Это тестовый фотоальбом $i и описание для него."))

            //добавляем фотографии в фотоальбомы
            for (j in 1..5) {
                var fileUid =  UUID.fromString("9663382f-af91-411e-bc7d-1b1f237229a1")
                var testPictureDto = PictureDto(
                        id = j.toLong(),
                        pictureUuid = fileUid,
                        photoAlbumId = savedDto.id,
                        uniqFileName = "$fileUid.jpg",
                        fileComment = "Это тестовый коментарий к фотографии №$j в фотоальбоме $i",
                        isDeleted = false);

                pictureRepository.save(testPictureDto);

            }

        }



        log.info("Test messages created!");

        // fetch all messages
        log.info("----------------------------------");
        messagesRepository.findAll().forEach {
            log.info(it.toString())
        }
        log.info("----------------------------------");
    }
}

fun main(args: Array<String>) {
    val runApplication = runApplication<SchoolApplication>(*args)
}
