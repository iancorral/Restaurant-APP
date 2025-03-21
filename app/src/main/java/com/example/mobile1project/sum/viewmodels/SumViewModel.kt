package com.example.mobile1project.sum.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class SumViewModel : ViewModel() {
    var number1 by mutableStateOf("")
    var number2 by mutableStateOf("")
    var result by mutableStateOf("")

    fun calculateSum() {
        val num1 = number1.toDoubleOrNull() ?: 0.0
        val num2 = number2.toDoubleOrNull() ?: 0.0
        result = (num1 + num2).toString()
    }
}
