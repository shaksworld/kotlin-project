package com.shaka.newproject.controller

import com.shaka.newproject.model.Users
import com.shaka.newproject.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (private val userService: UserService) {

    @GetMapping
    fun getUser(): Collection<Users> = userService.getUser()
    @GetMapping("/{accountNumber}")
    fun getUser(@PathVariable accountNumber: String): Users = userService.getUser(accountNumber)


}