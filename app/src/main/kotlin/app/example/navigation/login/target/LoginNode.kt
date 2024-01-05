package app.example.navigation.login.target

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import app.example.ui.screens.login.LoginScreen
import app.example.ui.screens.login.LoginViewModel
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node

class LoginNode(
    buildContext: BuildContext,
    private val onLogin: (id: String) -> Unit,
    private val viewModelFactory: LoginViewModel.Factory,
    private val viewModelStoreOwner: ViewModelStoreOwner,
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = viewModel<LoginViewModel>(
            factory = viewModelFactory,
            viewModelStoreOwner = viewModelStoreOwner,
        )

        LoginScreen(viewModel, onLogin)
    }
}
