plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.anvil)
}

android {
  namespace = "app.example.ui"
  compileSdk = Integer.parseInt(project.extra["compileSdk"].toString())

  defaultConfig {
    minSdk = Integer.parseInt(project.extra["minSdk"].toString())
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    freeCompilerArgs += "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }

  buildFeatures {
    compose = true
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

  implementation(platform(libs.compose.bom))
  implementation(libs.compose.ui)
  implementation(libs.compose.runtime)
  implementation(libs.compose.foundation)
  implementation(libs.compose.material3)
  implementation(libs.compose.material3.icons)

  implementation(libs.dagger)
  kapt(libs.dagger.compiler)

  implementation(libs.timber)
}
