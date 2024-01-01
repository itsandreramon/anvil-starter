package app.example.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.example.base.BaseActivity
import app.example.di.inject
import app.example.session.UserSessionManager
import app.example.theme.AppTheme
import app.example.theme.padding_medium
import app.example.ui.login.LoginViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var userSessionManager: UserSessionManager
    @Inject lateinit var viewModelFactory: LoginViewModel.Factory

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