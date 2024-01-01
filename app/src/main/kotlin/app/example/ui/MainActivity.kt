package app.example.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import app.example.navigation.AppNode
import app.example.theme.AppTheme
import com.bumble.appyx.navigation.integration.NodeActivity
import com.bumble.appyx.navigation.integration.NodeHost
import com.bumble.appyx.navigation.integrationpoint.IntegrationPoint
import com.bumble.appyx.navigation.platform.AndroidLifecycle

class MainActivity : NodeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        // installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                App(appyxV2IntegrationPoint)
            }
        }
    }
}

@Composable
private fun App(integrationPoint: IntegrationPoint) {
    val context = LocalContext.current.applicationContext

    NodeHost(
        lifecycle = AndroidLifecycle(LocalLifecycleOwner.current.lifecycle),
        integrationPoint = integrationPoint,
    ) {
        AppNode(
            buildContext = it,
            applicationContext = context,
        )
    }
}
