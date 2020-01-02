package com.wrestling.school

import com.wrestling.school.dtos.MessageDto
import com.wrestling.school.repositories.MessagesRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime

@SpringBootApplication
class SchoolApplication {
    private val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    fun init(repository: MessagesRepository) = CommandLineRunner {

        val currentDate = LocalDateTime.now()
        for(i in 1..100)
        {
            repository.save(MessageDto(i.toLong(), currentDate, currentDate, "Test message $i", "Text for message $i"));
        }

        log.info("Test messages created!");

        // fetch all messages
        log.info("----------------------------------");
        repository.findAll().forEach {
            log.info(it.toString())
        }
        log.info("----------------------------------");
    }
}

fun main(args: Array<String>) {
    val runApplication = runApplication<SchoolApplication>(*args)
}
