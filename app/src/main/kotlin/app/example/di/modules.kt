package app.example.di

import app.example.BuildConfig
import app.example.core.data.coroutines.CoroutineDispatchers
import app.example.core.di.AppScope
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.optional.SingleIn
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

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
  fun provideCoroutineDispatchers(): CoroutineDispatchers {
    return CoroutineDispatchers(
      io = Dispatchers.IO,
      db = Dispatchers.IO.limitedParallelism(1),
      main = Dispatchers.Main,
    )
  }
}
