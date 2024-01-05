package app.example

import android.app.Application
import app.example.core.data.AppCoroutineScope
import app.example.core.di.DaggerSet
import app.example.core.di.InitializerFunction
import app.example.di.AppComponent
import app.example.di.DaggerAppComponent
import app.example.di.UserSessionManager
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

  @Inject lateinit var userSessionManager: UserSessionManager

  @Inject lateinit var initializers: DaggerSet<InitializerFunction>

  private val appCoroutineScope: AppCoroutineScope by lazy {
    AppCoroutineScope(CoroutineScope(SupervisorJob()))
  }

  val appComponent: AppComponent by lazy {
    DaggerAppComponent
      .factory()
      .create(this, appCoroutineScope)
  }

  override fun onCreate() {
    super.onCreate()
    appComponent.inject(this)
    initializers.forEach { it() }
  }
}
