package app.example.navigation.home.target

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import app.example.ui.screens.home.HomeScreen
import app.example.ui.screens.home.HomeViewModel
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node

class HomeNode(
  buildContext: BuildContext,
  private val onLogout: () -> Unit,
  private val viewModelFactory: HomeViewModel.Factory,
  private val viewModelStoreOwner: ViewModelStoreOwner,
) : Node(buildContext) {

  @Composable
  override fun View(modifier: Modifier) {
    val viewModel = viewModel<HomeViewModel>(
      viewModelStoreOwner = viewModelStoreOwner,
      factory = viewModelFactory,
    )

    HomeScreen(viewModel, onLogout)
  }
}
