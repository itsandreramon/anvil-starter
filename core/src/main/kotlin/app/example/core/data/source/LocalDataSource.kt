package app.example.core.data.source

import app.example.core.data.AppCoroutineScope
import app.example.core.di.AppScope
import com.squareup.anvil.annotations.optional.SingleIn
import javax.inject.Inject
import timber.log.Timber

@SingleIn(AppScope::class)
class LocalDataSource @Inject constructor(
    coroutineScope: AppCoroutineScope,
) : DataSource {

    init {
        Timber.d("Creating data source: $this")
        Timber.d("Got coroutine scope: $coroutineScope")
    }

    override fun getData(): String {
        return "No user session found"
    }
}
