package app.example.navigation.home

import android.content.Context
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.example.di.UserSessionManager
import app.example.di.inject
import app.example.navigation.home.target.HomeNode
import app.example.navigation.login.LoginRouting
import app.example.ui.screens.home.HomeViewModel
import app.example.ui.util.ViewModelStoreOwnerNode
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.replace
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import javax.inject.Inject
import kotlinx.parcelize.Parcelize
import timber.log.Timber

sealed class HomeTarget : Parcelable {

    @Parcelize
    data object Home : HomeTarget()

    @Parcelize
    data object Login : HomeTarget()
}

class HomeRouting(
    buildContext: BuildContext,
    val applicationContext: Context,
    private val backStack: BackStack<HomeTarget> = BackStack(
        model = BackStackModel(
            initialTarget = HomeTarget.Home,
            savedStateMap = buildContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
    ),
) : ViewModelStoreOwnerNode<HomeTarget>(appyxComponent = backStack, buildContext = buildContext) {

    @Inject lateinit var userSessionManager: UserSessionManager

    @Inject lateinit var viewModelFactory: HomeViewModel.Factory

    init {
        inject(this)
    }

    override fun onBuilt() {
        super.onBuilt()
        Timber.d("onBuilt HomeRouting")
    }

    @Composable
    override fun View(modifier: Modifier) {
        AppyxComponent(
            appyxComponent = backStack,
            modifier = Modifier,
        )
    }

    override fun resolve(interactionTarget: HomeTarget, buildContext: BuildContext): Node {
        return when (interactionTarget) {
            HomeTarget.Home -> HomeNode(
                buildContext = buildContext,
                viewModelFactory = viewModelFactory,
                viewModelStoreOwner = viewModelStoreOwner,
                onLogout = {
                    userSessionManager.resetSession()
                    backStack.replace(HomeTarget.Login)
                },
            )

            HomeTarget.Login -> LoginRouting(buildContext, applicationContext)
        }
    }
}
