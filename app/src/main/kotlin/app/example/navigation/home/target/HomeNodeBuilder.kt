package app.example.navigation.home.target

import app.example.ui.screens.home.HomeViewModel
import app.example.ui.util.ViewModelStoreOwnerProvider
import com.bumble.appyx.navigation.builder.Builder
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.store.getRetainedInstance

class HomeNodeBuilder(
    private val onLogout: () -> Unit,
    private val viewModelFactory: HomeViewModel.Factory,
) : Builder<String>() {

    override fun build(buildContext: BuildContext, payload: String): Node {
        val viewModelStoreOwner = buildContext.getRetainedInstance(
            factory = { ViewModelStoreOwnerProvider.getOwner(payload) },
            disposer = { ViewModelStoreOwnerProvider.removeOwner(payload) },
        )

        return HomeNode(
            buildContext = buildContext,
            onLogout = onLogout,
            viewModelFactory = viewModelFactory,
            viewModelStoreOwner = viewModelStoreOwner,
        )
    }
}
