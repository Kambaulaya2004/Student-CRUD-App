package com.example.studentcrudapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentcrudapp.data.entity.Student
import com.example.studentcrudapp.data.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentViewModel (
    private val repository: StudentRepository
) : ViewModel() {
    private val _students = MutableStateFlow<List<Student>> (emptyList())
    val students: StateFlow<List<Student>> = _students

    init {
        viewModelScope.launch {
            repository.allStudents.collect { studentList ->
                _students.value = studentList
            }
        }
    }

    fun addStudent(student: Student) {
        viewModelScope.launch{
            repository.insert(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            repository.update(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            repository.delete(student)
        }
    }
}