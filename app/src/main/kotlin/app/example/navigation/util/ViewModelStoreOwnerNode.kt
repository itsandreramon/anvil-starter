package app.example.navigation.util

import androidx.lifecycle.ViewModelStoreOwner
import app.example.util.ViewModelStoreOwnerProvider
import com.bumble.appyx.interactions.core.model.AppyxComponent
import com.bumble.appyx.navigation.lifecycle.Lifecycle
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.ParentNode

abstract class ViewModelStoreOwnerNode<InteractionTarget : Any>(
    appyxComponent: AppyxComponent<InteractionTarget, *>,
    val buildContext: BuildContext,
) : ParentNode<InteractionTarget>(appyxComponent, buildContext) {

    val viewModelStoreOwner: ViewModelStoreOwner
        get() = ViewModelStoreOwnerProvider.getOwner(id)

    override fun updateLifecycleState(state: Lifecycle.State) {
        super.updateLifecycleState(state)

        if (state == Lifecycle.State.DESTROYED) {
            if (!integrationPoint.isChangingConfigurations) {
                ViewModelStoreOwnerProvider.removeOwner(id)
            }
        }
    }
}
