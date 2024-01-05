package app.example.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.example.navigation.AppContainer
import app.example.ui.theme.AppTheme
import com.bumble.appyx.navigation.integration.NodeActivity

class MainActivity : NodeActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    setContent {
      AppTheme {
        AppContainer(appyxV2IntegrationPoint)
      }
    }
  }
}
