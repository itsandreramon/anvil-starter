package com.example.anvil.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.anvil.R
import com.example.anvil.di.inject
import com.example.anvil.session.UserSessionManager
import com.example.anvil.ui.login.LoginViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject lateinit var userSessionManager: UserSessionManager
    @Inject lateinit var viewModelFactory: LoginViewModel.Factory

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(this)
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
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Text(text = "Hello from Compose!")
            }
        },
    )
}