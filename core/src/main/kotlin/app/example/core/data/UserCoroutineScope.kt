package app.example.core.data

import kotlinx.coroutines.CoroutineScope

class UserCoroutineScope(
    private val parentScope: CoroutineScope,
) : CoroutineScope by parentScope
