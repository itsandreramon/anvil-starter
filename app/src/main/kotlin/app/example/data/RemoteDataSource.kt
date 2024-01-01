package app.example.data

import app.example.di.UserScope
import app.example.session.UserCoroutineScope
import app.example.session.UserSession
import com.squareup.anvil.annotations.optional.SingleIn
import javax.inject.Inject
import timber.log.Timber

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
