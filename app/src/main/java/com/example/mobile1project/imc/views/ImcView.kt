package com.example.mobile1project.imc.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile1project.R
import com.example.mobile1project.imc.viewmodels.IMCViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

@Composable
fun IMCScreen(imcViewModel: IMCViewModel = viewModel()) {
    val resources = LocalContext.current.resources

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = imcViewModel.weight,
            onValueChange = { imcViewModel.weight = it },
            label = { Text(stringResource(R.string.weight_label)) },
            modifier = Modifier.fillMaxWidth(),
            isError = imcViewModel.errorMessage.isNotEmpty()
        )

        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = imcViewModel.height,
            onValueChange = { imcViewModel.height = it },
            label = { Text(stringResource(R.string.height_label)) },
            modifier = Modifier.fillMaxWidth(),
            isError = imcViewModel.errorMessage.isNotEmpty()
        )

        if (imcViewModel.errorMessage.isNotEmpty()) {
            Text(
                text = imcViewModel.errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { imcViewModel.calculateBMI(resources) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.calculate_button))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (imcViewModel.bmiResult.isNotEmpty()) {
            Text(text = imcViewModel.bmiResult, style = MaterialTheme.typography.titleLarge)
        }

        if (imcViewModel.evaluation.isNotEmpty()) {
            Text(text = imcViewModel.evaluation, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
