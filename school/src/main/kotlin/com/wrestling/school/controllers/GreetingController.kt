package com.wrestling.school.controllers

import com.wrestling.school.repositories.MessagesRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class GreetingController (private val repository: MessagesRepository){

    @Value("\${spring.profiles.active}")
    private val profile: String? = null

    @GetMapping
    fun index(model: Model): String {
        val data = HashMap<Any, Any>()

        model.addAttribute("frontendData", data)
        model.addAttribute("isDevMode", "dev" == profile)

        return "index"
    }
}