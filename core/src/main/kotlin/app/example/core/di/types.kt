package app.example.core.di

typealias DaggerSet<T> = @JvmSuppressWildcards Set<T>

typealias InitializerFunction = () -> @JvmSuppressWildcards Unit

interface DaggerComponentOwner {
  val daggerComponent: Any
}
