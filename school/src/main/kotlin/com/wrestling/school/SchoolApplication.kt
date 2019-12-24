package com.wrestling.school

import com.wrestling.school.dtos.MessageDto
import com.wrestling.school.repositories.MessagesRepository
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SchoolApplication {
    private val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    fun init(repository: MessagesRepository) = CommandLineRunner {

        for(i in 1..100)
        {
            repository.save(MessageDto(i.toLong(), "Test message $i", "Text for message $i"));
        }

        /*
        repository.save(MessageDto(0, "Test message {i}", "Text for message 0"));
        repository.save(MessageDto(1, "Test message 1", "Text for message 1"));
        repository.save(MessageDto(2, "Test message 2", "Text for message 2"));
        */

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
