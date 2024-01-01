package app.example.util

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import timber.log.Timber

object ViewModelStoreOwnerProvider {

    private val storeOwnerMap = mutableMapOf<String, ViewModelStoreOwner>()

    fun getOwner(key: String): ViewModelStoreOwner {
        Timber.d("Getting owner for key: $key")
        return storeOwnerMap.getOrPut(key) {
            object : ViewModelStoreOwner {
                override val viewModelStore = ViewModelStore()
            }
        }
    }

    fun removeOwner(key: String) {
        Timber.d("Removing owner for key: $key")
        storeOwnerMap[key]?.viewModelStore!!.clear()
        storeOwnerMap.remove(key)
    }
}
