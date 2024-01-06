package app.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import app.example.ui.MainActivity
import com.bumble.appyx.navigation.integration.NodeHost
import com.bumble.appyx.navigation.platform.AndroidLifecycle

@Composable
fun AppContainer(mainActivity: MainActivity) {
  val context = LocalContext.current.applicationContext
  val lifecycle = LocalLifecycleOwner.current.lifecycle

  NodeHost(
    lifecycle = AndroidLifecycle(lifecycle),
    integrationPoint = mainActivity.appyxV2IntegrationPoint,
  ) { buildContext ->
    AppNode(buildContext, context)
  }
}
