package app.example.navigation

import android.content.Context
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.example.navigation.login.LoginRouting
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import kotlinx.parcelize.Parcelize

sealed class AppTarget : Parcelable {

    @Parcelize
    data object Login : AppTarget()
}

class AppNode(
    buildContext: BuildContext,
    private val applicationContext: Context,
    private val backStack: BackStack<AppTarget> = BackStack(
        model = BackStackModel(
            initialTarget = AppTarget.Login,
            savedStateMap = buildContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
    ),
) : ParentNode<AppTarget>(appyxComponent = backStack, buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        AppyxComponent(
            appyxComponent = backStack,
            modifier = Modifier,
        )
    }

    override fun resolve(interactionTarget: AppTarget, buildContext: BuildContext): Node {
        return when (interactionTarget) {
            AppTarget.Login -> LoginRouting(
                buildContext,
                applicationContext,
            )
        }
    }
}
