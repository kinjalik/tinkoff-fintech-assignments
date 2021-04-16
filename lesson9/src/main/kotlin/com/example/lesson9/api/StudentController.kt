package com.example.lesson9.api

import com.example.lesson9.dao.StudentDao
import com.example.lesson9.exceptions.StudentNotFoundException
import com.example.lesson9.integration.FacultyIntegrationComponent
import com.example.lesson9.model.Faculty
import com.example.lesson9.model.Student
import com.example.lesson9.model.StudentTemplate
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
class StudentController(
    private val facultyIntegration: FacultyIntegrationComponent
) {
    @ApiOperation("Get all students")
    @GetMapping
    fun getStudents(): List<Student> {
        return StudentDao().getAll().map {
            val fac = facultyIntegration.getFacultyById(it.facultyId) ?: Faculty(0, "Unknown", 0)
            Student(it, fac)
        }
    }

    @ApiOperation("Get student with specified ID")
    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: Int): Student {
        val res = StudentDao().getById(id)
        if (res == null)
            throw StudentNotFoundException()
        val fac = facultyIntegration.getFacultyById(res.facultyId) ?: Faculty(0, "Unknown", 0)
        return Student(res, fac)
    }

    @ApiOperation("Add student")
    @PostMapping
    fun addStudent(@RequestBody student: StudentTemplate) {
        println("Added student: $student")
    }

    @ApiOperation("Edit student with specified ID")
    @PutMapping("/{id}")
    fun editStudentById(@PathVariable id: Int, @RequestBody student: StudentTemplate) {
        println("Edited student with id $id. New values: $student")
    }

    @ApiOperation("Delete student with specified ID")
    @DeleteMapping("/{id}")
    fun deleteStudentById(@PathVariable id: Int) {
        println("Deleted student with id $id")
    }
}