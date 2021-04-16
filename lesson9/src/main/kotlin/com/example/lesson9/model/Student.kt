package com.example.lesson9.model

import io.swagger.annotations.ApiModel

@ApiModel("Student")
data class Student(
    val id: Int,
    val name: String,
    val surname: String,
    val birthday: String,
    val enrollmentYear: Int,
    val facultyId: Int,
    val faculty: Faculty
) {
    constructor(raw: StudentRaw, fac: Faculty) : this(
        raw.id,
        raw.name,
        raw.surname,
        raw.birthday,
        raw.enrollmentYear,
        raw.facultyId,
        fac
    )
}
