package com.example.mobile1project.login.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobile1project.ui.theme.Mobile1ProjectTheme

@Composable
fun LoginView() {
    Column {
        Text(
            text = "Login",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    Mobile1ProjectTheme {
        LoginView()
    }
}
