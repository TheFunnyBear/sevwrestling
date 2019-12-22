package com.wrestling.school

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SchoolApplication

fun main(args: Array<String>) {
    val runApplication = runApplication<SchoolApplication>(*args)
}
