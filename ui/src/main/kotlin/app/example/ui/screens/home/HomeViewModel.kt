package app.example.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.example.core.data.source.RemoteDataSource
import javax.inject.Inject
import timber.log.Timber

class HomeViewModel(
    private val remoteDataSource: RemoteDataSource,
) : ViewModel() {

    init {
        Timber.d("Created home view model: $this")
    }

    fun getData(): String {
        return remoteDataSource.getData()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val remoteDataSource: RemoteDataSource,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(remoteDataSource) as T
        }
    }
}
