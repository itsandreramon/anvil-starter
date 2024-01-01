package com.example.anvil.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.anvil.di.inject
import com.example.anvil.session.UserSessionManager
import com.example.anvil.theme.AppTheme
import com.example.anvil.theme.padding_medium
import com.example.anvil.ui.login.LoginViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject lateinit var userSessionManager: UserSessionManager
    @Inject lateinit var viewModelFactory: LoginViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(this)
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                AppContainer()
            }
        }
    }
}

@Composable
fun AppContainer() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Login") }
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Text(
                    text = "Hello from Compose!",
                    modifier = Modifier.padding(
                        horizontal = padding_medium,
                    )
                )
            }
        },
    )
}