package app.example

import android.app.Application
import app.example.di.AppComponent
import app.example.di.DaggerAppComponent
import app.example.di.DaggerSet
import app.example.di.InitializerFunction
import app.example.session.UserSessionManager
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AppCoroutineScope(
    private val parentScope: CoroutineScope,
) : CoroutineScope by parentScope

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
