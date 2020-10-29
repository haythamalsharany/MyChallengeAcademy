package com.alsharany.mychallengeacademy

import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    val Students = mutableListOf<Student>()

    init {
        for (i in 0 until 100) {
            val student =
                Student()
            student.name = "haytham #$i"
            student.number = i
            student.passed = i % 2 == 0
            Students += student
        }
    }

    fun addStudent(student: Student) {
        Students.add(student)
    }

    fun deleteStudent(index: Int) {
        Students.removeAt(index)

    }


}