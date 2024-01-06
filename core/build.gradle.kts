plugins {
  alias(libs.plugins.example.android.library)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.anvil)
}

android {
  namespace = "app.example.core"
}

dependencies {
  implementation(libs.androidx.core)
  implementation(libs.coroutines.core)
  implementation(libs.timber)

  api(libs.anvil.annotations)

  implementation(libs.dagger)
  kapt(libs.dagger.compiler)
}
