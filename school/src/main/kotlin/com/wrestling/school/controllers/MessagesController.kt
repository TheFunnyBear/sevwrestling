package com.wrestling.school.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessagesController {

    @GetMapping("/greeting")
    fun greeting() : String
    {
        return "Hi!!!";
    }
}