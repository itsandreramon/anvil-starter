package app.example.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.example.core.data.source.LocalDataSource
import javax.inject.Inject
import timber.log.Timber

class LoginViewModel(
    private val localDataSource: LocalDataSource,
) : ViewModel() {

    init {
        Timber.d("Created login view model: $this")
    }

    fun getData(): String {
        return localDataSource.getData()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val localDataSource: LocalDataSource,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(localDataSource) as T
        }
    }
}
