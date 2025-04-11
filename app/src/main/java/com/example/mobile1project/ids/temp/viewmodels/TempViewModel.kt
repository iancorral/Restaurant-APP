package com.example.mobile1project.ids.temp.viewmodels

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.mobile1project.R

class TempViewModel : ViewModel() {
    var celsius by mutableStateOf("")
    var fahrenheit by mutableStateOf("")
    var errorMessage by mutableStateOf("")

    fun convertTemperature(resources: Resources) {
        val celsiusValue = celsius.toFloatOrNull()

        if (celsiusValue == null) {
            errorMessage = resources.getString(R.string.error_invalid_inp)
            fahrenheit = ""
        } else {
            errorMessage = ""
            val result = (celsiusValue * 9 / 5) + 32
            fahrenheit = resources.getString(R.string.fahrenheit_result, result)
        }
    }
}
