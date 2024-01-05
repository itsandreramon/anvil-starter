package app.example.navigation.login.target

import app.example.ui.screens.login.LoginViewModel
import app.example.ui.util.ViewModelStoreOwnerProvider
import com.bumble.appyx.navigation.builder.SimpleBuilder
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.store.getRetainedInstance

class LoginNodeBuilder(
  private val onLogin: (id: String) -> Unit,
  private val viewModelFactory: LoginViewModel.Factory,
) : SimpleBuilder() {

  override fun build(buildContext: BuildContext): Node {
    val viewModelStoreKey = buildContext.identifier
    val viewModelStoreOwner = buildContext.getRetainedInstance(
      factory = { ViewModelStoreOwnerProvider.getOwner(viewModelStoreKey) },
      disposer = { ViewModelStoreOwnerProvider.removeOwner(viewModelStoreKey) },
    )

    return LoginNode(
      buildContext = buildContext,
      onLogin = onLogin,
      viewModelFactory = viewModelFactory,
      viewModelStoreOwner = viewModelStoreOwner,
    )
  }
}
