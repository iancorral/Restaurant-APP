package com.example.mobile1project.ids.examen.repository

import com.example.mobile1project.ids.examen.models.Student
import com.example.mobile1project.ids.examen.network.ExamenApiService
import retrofit2.HttpException
import java.io.IOException

class ExamenRepository(private val apiService: ExamenApiService) {
    suspend fun fetchStudents(): List<Student> {
        try {
            return apiService.getStudents()
        } catch (e: IOException) {
            // Sin conexión a Internet
            throw Exception("Sin conexión a Internet")
        } catch (e: HttpException) {
            // Errores HTTP
            when (e.code()) {
                404 -> throw Exception("Error 404: Recurso no encontrado")
                500 -> throw Exception("Error 500: Error del servidor")
                else -> throw Exception("Error de red: ${e.message()}")
            }
        } catch (e: Exception) {
            throw Exception("Error desconocido: ${e.localizedMessage}")
        }
    }
}