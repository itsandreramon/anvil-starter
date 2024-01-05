package app.example.navigation.login.target

import app.example.ui.screens.login.LoginViewModel
import app.example.ui.util.ViewModelStoreOwnerProvider
import com.bumble.appyx.navigation.builder.Builder
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.store.getRetainedInstance

class LoginNodeBuilder(
    private val onLogin: (id: String) -> Unit,
    private val viewModelFactory: LoginViewModel.Factory
) : Builder<String>() {

    override fun build(buildContext: BuildContext, payload: String): Node {
        val viewModelStoreOwner = buildContext.getRetainedInstance(
            factory = { ViewModelStoreOwnerProvider.getOwner(payload) },
            disposer = { ViewModelStoreOwnerProvider.removeOwner(payload) },
        )

        return LoginNode(
            buildContext = buildContext,
            onLogin = onLogin,
            viewModelFactory = viewModelFactory,
            viewModelStoreOwner = viewModelStoreOwner,
        )
    }
}
