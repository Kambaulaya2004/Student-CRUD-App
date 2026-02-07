package com.example.studentcrudapp.data.dao

import androidx.room.*. //Importing all Annotations.
import kotlinx.coroutines.flow.Flow
import com.example.studentcrudapp.data.entity.Student

@Dao
interface StudentDao {
    @Query ("SELECT * FROM student")
    fun getAllStudents(): Flow<List<Student>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student:Student)
}
