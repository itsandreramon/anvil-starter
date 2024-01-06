plugins {
  alias(libs.plugins.example.android.library)
  alias(libs.plugins.example.android.library.compose)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.anvil)
}

android {
  namespace = "app.example.ui"

  kotlinOptions {
    freeCompilerArgs += "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
  }
}

dependencies {
  implementation(project(":core"))

  implementation(libs.androidx.activity)
  implementation(libs.androidx.viewmodel)
  api(libs.androidx.activity.compose)
  api(libs.androidx.viewmodel.compose)

  api(libs.appyx.android)
  api(libs.appyx.backstack)

  implementation(libs.coil.core)
  implementation(libs.coil.compose)

  implementation(libs.compose.ui)
  implementation(libs.compose.runtime)
  implementation(libs.compose.foundation)
  implementation(libs.compose.material3)
  implementation(libs.compose.material3.icons)

  implementation(libs.dagger)
  kapt(libs.dagger.compiler)

  implementation(libs.timber)
}
