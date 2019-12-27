package com.wrestling.school.controllers

import com.wrestling.school.repositories.MessagesRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/news")
class NewsController (private val repository: MessagesRepository) {
    /**
     * Просмотр страницы списка новости
     */
    @GetMapping("list")
    fun getNewsListPage(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") pageNumber: Int?,
                        model: Model): String {
        model.addAttribute("pageNumber", pageNumber);

        return "news/list"
    }

    /**
     * Просмотр новости
     */
    @GetMapping("details")
    fun getNewsDetailsPage(@RequestParam(name = "newsNumber", required = true) newsNumber: Long,
                           model: Model): String {
        model.addAttribute("newsNumber", newsNumber);

        return "news/details"
    }

    /**
     * Добавление новой новости
     */
    @GetMapping("add")
    fun addNewsPage(model: Model): String {
        return "news/add"
    }

    /**
     * Управление новостями
     */
    @GetMapping("manage")
    fun manageNewsPage(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") pageNumber: Int?,
                       model: Model): String {
        model.addAttribute("pageNumber", pageNumber);

        return "news/manage"
    }

}