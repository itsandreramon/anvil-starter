package app.example.core.data

import kotlinx.coroutines.CoroutineScope

class AppCoroutineScope(
    private val parentScope: CoroutineScope,
) : CoroutineScope by parentScope
