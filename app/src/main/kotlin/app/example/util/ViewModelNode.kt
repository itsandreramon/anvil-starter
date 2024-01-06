package app.example.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.ParentNode
import com.bumble.appyx.navigation.store.getRetainedInstance

abstract class ViewModelNode<InteractionTarget : Any>(
  buildContext: BuildContext,
  private val backStack: BackStack<InteractionTarget>,
) : ParentNode<InteractionTarget>(backStack, buildContext), ViewModelStoreOwner {

  private val _viewModelStore by lazy { ViewModelStore() }

  override val viewModelStore = buildContext.getRetainedInstance(
    factory = { _viewModelStore },
    disposer = { _viewModelStore.clear() },
  )

  @Composable
  override fun View(modifier: Modifier) {
    CompositionLocalProvider(LocalViewModelStoreOwner provides this) {
      AppyxComponent(
        appyxComponent = backStack,
        modifier = Modifier,
      )
    }
  }
}
