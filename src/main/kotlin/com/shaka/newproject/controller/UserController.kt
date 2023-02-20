package com.shaka.newproject.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/work")
class UserController {

    @GetMapping
    fun work(): String {
        return "Work"
    }
}