package com.shaka.newproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NewProjectApplication

fun main(args: Array<String>) {
	runApplication<NewProjectApplication>(*args)
}
