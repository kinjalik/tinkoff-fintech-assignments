package com.example.lesson9

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class Lesson9Application

fun main(args: Array<String>) {
    runApplication<Lesson9Application>(*args)
}
