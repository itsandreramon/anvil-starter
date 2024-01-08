package app.example.di

import app.example.BuildConfig
import app.example.core.data.coroutines.AppCoroutineScope
import app.example.core.data.coroutines.CoroutineDispatchers
import app.example.core.di.AppScope
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.optional.SingleIn
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

private const val DB_PARALLELISM = 1

@Module
@ContributesTo(AppScope::class)
object AppModule {

  @Provides
  @IntoSet
  fun timberInitializer(): () -> Unit = {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  @Provides
  @SingleIn(AppScope::class)
  fun provideAppCoroutineScope(
    dispatchers: CoroutineDispatchers,
  ): AppCoroutineScope {
    val parentScope = CoroutineScope(dispatchers.main + SupervisorJob())
    return AppCoroutineScope(parentScope)
  }

  @Provides
  @SingleIn(AppScope::class)
  fun provideCoroutineDispatchers(): CoroutineDispatchers {
    return CoroutineDispatchers(
      io = Dispatchers.IO,
      db = Dispatchers.IO.limitedParallelism(DB_PARALLELISM),
      main = Dispatchers.Main,
    )
  }
}
