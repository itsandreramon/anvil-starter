package app.example.core.data.coroutines

import kotlinx.coroutines.CoroutineScope

class AppCoroutineScope(
  private val parentScope: CoroutineScope,
) : CoroutineScope by parentScope

class UserCoroutineScope(
  private val parentScope: CoroutineScope,
) : CoroutineScope by parentScope
