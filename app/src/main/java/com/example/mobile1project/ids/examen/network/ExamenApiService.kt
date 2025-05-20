package com.example.mobile1project.ids.examen.network

import com.example.mobile1project.ids.examen.models.Student
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ExamenApiService {
    @GET("2b6648feaf70d1116e6f468b263d73c9/raw/50cf034eb87e273d6746cf2ffb9afb5a409f80b2/students.json")
    suspend fun getStudents(): List<Student>

    companion object {
        private const val BASE_URL = "https://gist.githubusercontent.com/ingjromo/"

        fun create(): ExamenApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExamenApiService::class.java)
        }
    }
}
