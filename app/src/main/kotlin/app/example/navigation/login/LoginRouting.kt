package app.example.navigation.login

import android.content.Context
import android.os.Parcelable
import app.example.di.UserSessionManager
import app.example.di.inject
import app.example.navigation.home.HomeRouting
import app.example.navigation.login.target.LoginNode
import app.example.ui.screens.login.LoginViewModel
import app.example.util.ViewModelNode
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.replace
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
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
) : ViewModelNode<LoginTarget>(buildContext, backStack) {

  @Inject lateinit var userSessionManager: UserSessionManager

  @Inject lateinit var viewModelFactory: LoginViewModel.Factory

  init {
    inject(this)
  }

  override fun onBuilt() {
    super.onBuilt()
    Timber.d("onBuilt LoginRouting")
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
