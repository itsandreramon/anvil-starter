package app.example.ui.screens.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import app.example.ui.theme.padding_medium

@Composable
fun LoginScreen(viewModel: LoginViewModel, onLogin: (String) -> Unit) {
    val data by produceState(initialValue = "") {
        value = viewModel.getData()
    }

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = padding_medium),
            ) {
                Column {
                    Text(text = data)
                    Spacer(modifier = Modifier.height(4.dp))
                    var textFieldState by remember {
                        mutableStateOf(TextFieldValue(""))
                    }
                    TextField(
                        value = textFieldState,
                        onValueChange = {
                            textFieldState = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(onClick = { onLogin(textFieldState.text) }) {
                        Text(text = "Login")
                    }
                }
            }
        },
    )
}
