package com.example.lesson9.api

import com.example.lesson9.dao.FacultyDAO
import com.example.lesson9.exceptions.FacultyNotFound
import com.example.lesson9.model.Faculty
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/faculties")
class FacultyController {
    @GetMapping
    fun getAll(): List<Faculty> {
        return FacultyDAO().getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): Faculty {
        val res = FacultyDAO().getById(id)
        if (res == null)
            throw FacultyNotFound()
        return res
    }
}