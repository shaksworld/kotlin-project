package com.shaka.newproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user") // This means URL's start with /user (after Application path)
public class UserController {

    @GetMapping
    fun h1(): String = "Hello World"
}
