package app.example.di

import app.example.App
import app.example.core.data.AppCoroutineScope
import app.example.core.data.UserCoroutineScope
import app.example.core.di.AppScope
import app.example.core.di.UserScope
import app.example.core.session.UserSession
import app.example.navigation.home.HomeRouting
import app.example.navigation.login.LoginRouting
import com.squareup.anvil.annotations.ContributesBinding
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.MergeSubcomponent
import com.squareup.anvil.annotations.optional.SingleIn
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import timber.log.Timber

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppComponent {

  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance app: App,
      @BindsInstance appCoroutineScope: AppCoroutineScope,
    ): AppComponent
  }

  fun userComponentFactory(): UserComponent.Factory

  fun inject(app: App)

  fun inject(loginRouting: LoginRouting)
}

@SingleIn(UserScope::class)
@MergeSubcomponent(UserScope::class)
interface UserComponent {

  @Subcomponent.Factory
  interface Factory {
    fun create(
      @BindsInstance userSession: UserSession,
      @BindsInstance userCoroutineScope: UserCoroutineScope,
    ): UserComponent
  }

  fun inject(homeRouting: HomeRouting)
}

/**
 * Manages the lifecycle of the UserComponent by creating
 * it only when provided with a valid UserSession object.
 *
 * Modules included in the UserComponent and its descendents can safely
 * inject a UserSession object and will be recreated if a new UserSession
 * object is created.
 *
 * @property userComponent Stores dependencies scoped to a UserSession.
 */
interface UserSessionManager {

  val userComponent: UserComponent?

  fun resetSession()

  fun createSession(id: String)

  @SingleIn(AppScope::class)
  @ContributesBinding(AppScope::class)
  class Impl @Inject constructor(
    private val app: App,
  ) : UserSessionManager {

    private var _userCoroutineScope: UserCoroutineScope? = null

    private val userCoroutineScope: UserCoroutineScope
      get() = _userCoroutineScope ?: createUserCoroutineScope()

    override var userComponent: UserComponent? = null
      private set

    override fun resetSession() {
      Timber.d("reset session...")
      _userCoroutineScope?.cancel()
      _userCoroutineScope = null
      userComponent = null
    }

    override fun createSession(id: String) {
      Timber.d("create session...")
      val session = UserSession(id)

      userComponent = app.appComponent
        .userComponentFactory()
        .create(session, userCoroutineScope)
    }

    private fun createUserCoroutineScope(): UserCoroutineScope {
      return _userCoroutineScope ?: UserCoroutineScope(
        CoroutineScope(SupervisorJob()),
      ).also { _userCoroutineScope = it }
    }
  }
}
