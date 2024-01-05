package app.example.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.example.ui.theme.padding_medium
import app.example.ui.theme.padding_small

@Composable
fun HomeScreen(viewModel: HomeViewModel, onLogout: () -> Unit) {
    val data by produceState(initialValue = "") {
        value = viewModel.getData()
    }

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Column(modifier = Modifier.padding(padding_medium, padding_small)) {
                    Text(text = data)
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(onClick = onLogout) {
                        Text(text = "Logout")
                    }
                }
            }
        },
    )
}
