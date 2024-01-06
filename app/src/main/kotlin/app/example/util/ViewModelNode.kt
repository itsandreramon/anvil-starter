package app.example.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.store.getRetainedInstance

abstract class ViewModelNode(
  buildContext: BuildContext,
) : Node(buildContext), ViewModelStoreOwner {

  private val _viewModelStore by lazy { ViewModelStore() }

  override val viewModelStore = buildContext.getRetainedInstance(
    factory = { _viewModelStore },
    disposer = { _viewModelStore.clear() },
  )
}
