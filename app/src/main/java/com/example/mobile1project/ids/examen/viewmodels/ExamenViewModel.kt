package com.example.mobile1project.ids.examen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile1project.ids.examen.models.Student
import com.example.mobile1project.ids.examen.network.ExamenApiService
import com.example.mobile1project.ids.examen.repository.ExamenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExamenViewModel : ViewModel() {

    private val apiService = ExamenApiService.create()
    private val repository = ExamenRepository(apiService)

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadStudents() {
        viewModelScope.launch {
            try {
                _students.value = repository.fetchStudents()
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    init {
        loadStudents()
    }
}