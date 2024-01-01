package app.example.data

import app.example.session.UserCoroutineScope
import app.example.session.UserSession
import app.example.di.UserScope
import com.squareup.anvil.annotations.optional.SingleIn
import timber.log.Timber
import javax.inject.Inject

@SingleIn(UserScope::class)
class RemoteDataSource @Inject constructor(
    private val userSession: UserSession,
    coroutineScope: UserCoroutineScope,
) : DataSource {

    init {
        Timber.d("Creating data source: $this")
        Timber.d("Got coroutine scope: $coroutineScope")
    }

    override fun getData(): String {
        return "Some data for user ${userSession.id}"
    }
}