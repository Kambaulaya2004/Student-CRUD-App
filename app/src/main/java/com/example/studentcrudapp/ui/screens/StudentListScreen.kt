package com.example.studentcrudapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentcrudapp.data.entity.Student
import com.example.studentcrudapp.viewmodel.StudentViewModel
  
@Composable
fun StudentListScreen(viewModel: StudentViewModel) {

    val students by viewModel.students.collectAsState()

    var name by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = course,
            onValueChange = { course = it },
            label = { Text("Course") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && course.isNotBlank()) {
                    viewModel.addStudent(
                        Student(name = name, course = course)
                    )
                    name = ""
                    course = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Student")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(students) { student ->
                Text(
                    text = "${student.name} - ${student.course}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
