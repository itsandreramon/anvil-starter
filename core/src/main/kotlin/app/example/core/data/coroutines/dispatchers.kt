package app.example.core.data.coroutines

import kotlinx.coroutines.CoroutineDispatcher

data class CoroutineDispatchers(
  val io: CoroutineDispatcher,
  val db: CoroutineDispatcher,
  val main: CoroutineDispatcher,
)
