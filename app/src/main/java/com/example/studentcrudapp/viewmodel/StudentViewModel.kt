package com.example.studentcrudapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentcrudapp.data.repository.StudentRepository
import com.example.studentcrudapp.data.entity.Student
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow

class StudentViewModel(private var repository: StudentRepository) : ViewModel() {
    private var _students = MutableStateFlow<List<Student>> (emptyList())
    var students: StateFlow<List<Student>> = _students

    init {
        viewModelScope.launch {
            repository.allStudents.collect {
                studentList -> _students.value = studentList
            }
        }
    }

    fun addStudent(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch {
        repository.update(student)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch {
        repository.delete(student)
    }
}