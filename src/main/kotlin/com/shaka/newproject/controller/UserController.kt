package com.shaka.newproject.controller

import com.shaka.newproject.model.Users
import com.shaka.newproject.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController (private val userService: UserService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

//    @ExceptionHandler(IllegalAccessException::class)
//    fun handleBadRequest(e: IllegalAccessException): ResponseEntity<String> =
//        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getUser(): Collection<Users> = userService.getUser()
    @GetMapping("/{name}")
    fun getUser(@PathVariable name: String): Users = userService.getUser(name)

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody users: Users): Users = userService.addUser(users)

    @PatchMapping
    fun updateUser(@RequestBody users: Users): Users = userService.updateUser(users)

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable name: String): Unit = userService.deleteUser(name)
}