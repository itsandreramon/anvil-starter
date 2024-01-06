package app.example.navigation.login

import android.content.Context
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.example.di.UserSessionManager
import app.example.di.inject
import app.example.navigation.home.HomeRouting
import app.example.navigation.login.target.LoginNode
import app.example.ui.screens.login.LoginViewModel
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.replace
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import javax.inject.Inject
import kotlinx.parcelize.Parcelize
import timber.log.Timber

sealed class LoginTarget : Parcelable {

  @Parcelize
  data object Login : LoginTarget()

  @Parcelize
  data object Home : LoginTarget()
}

class LoginRouting(
  buildContext: BuildContext,
  val applicationContext: Context,
  private val backStack: BackStack<LoginTarget> = BackStack(
    model = BackStackModel(
      initialTarget = LoginTarget.Login,
      savedStateMap = buildContext.savedStateMap,
    ),
    visualisation = { BackStackFader(it) },
  ),
) : ParentNode<LoginTarget>(appyxComponent = backStack, buildContext = buildContext) {

  @Inject lateinit var userSessionManager: UserSessionManager

  @Inject lateinit var viewModelFactory: LoginViewModel.Factory

  init {
    inject(this)
  }

  override fun onBuilt() {
    super.onBuilt()
    Timber.d("onBuilt LoginRouting")
  }

  @Composable
  override fun View(modifier: Modifier) {
    AppyxComponent(
      appyxComponent = backStack,
      modifier = Modifier,
    )
  }

  override fun resolve(interactionTarget: LoginTarget, buildContext: BuildContext): Node {
    return when (interactionTarget) {
      LoginTarget.Login -> LoginNode(
        buildContext = buildContext,
        viewModelFactory = viewModelFactory,
        onLogin = { id ->
          userSessionManager.createSession(id)
          backStack.replace(LoginTarget.Home)
        },
      )

      LoginTarget.Home -> HomeRouting(
        buildContext = buildContext,
        applicationContext = applicationContext,
      )
    }
  }
}
