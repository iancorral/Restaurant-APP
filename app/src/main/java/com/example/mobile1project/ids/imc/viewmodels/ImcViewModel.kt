package com.example.mobile1project.ids.imc.viewmodels

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.mobile1project.R

class IMCViewModel : ViewModel() {
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var bmiResult by mutableStateOf("")
    var evaluation by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    fun calculateBMI(resources: Resources) {
        val weightValue = weight.toFloatOrNull()
        val heightValue = height.toFloatOrNull()

        if (weightValue == null || heightValue == null || heightValue <= 0) {
            errorMessage = resources.getString(R.string.error_invalid_input)
            bmiResult = ""
            evaluation = ""
        } else {
            errorMessage = ""
            val bmi = weightValue / (heightValue * heightValue)
            bmiResult = resources.getString(R.string.bmi_result, bmi)

            evaluation = if (bmi in 19.0..24.9) {
                resources.getString(R.string.bmi_normal)
            } else {
                resources.getString(R.string.bmi_not_normal)
            }
        }
    }
}
