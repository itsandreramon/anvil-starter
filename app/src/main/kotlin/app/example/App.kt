package app.example

import android.app.Application
import app.example.core.di.DaggerSet
import app.example.core.di.InitializerFunction
import app.example.di.AppComponent
import app.example.di.DaggerAppComponent
import app.example.di.UserSessionManager
import javax.inject.Inject

class App : Application() {

  @Inject lateinit var userSessionManager: UserSessionManager

  @Inject lateinit var initializers: DaggerSet<InitializerFunction>

  val appComponent: AppComponent by lazy {
    DaggerAppComponent
      .factory()
      .create(this)
  }

  override fun onCreate() {
    super.onCreate()
    appComponent.inject(this)
    initializers.forEach { it() }
  }
}
