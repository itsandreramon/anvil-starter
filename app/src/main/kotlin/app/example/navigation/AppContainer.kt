package app.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.bumble.appyx.navigation.integration.NodeHost
import com.bumble.appyx.navigation.integrationpoint.IntegrationPoint
import com.bumble.appyx.navigation.platform.AndroidLifecycle

@Composable
fun AppContainer(integrationPoint: IntegrationPoint) {
  val context = LocalContext.current.applicationContext
  val lifecycle = LocalLifecycleOwner.current.lifecycle

  NodeHost(AndroidLifecycle(lifecycle), integrationPoint) { buildContext ->
    AppNode(buildContext, context)
  }
}
