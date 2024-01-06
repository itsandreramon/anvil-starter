plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.anvil)
}

android {
  namespace = "app.example.core"
  compileSdk = Integer.parseInt(project.extra["compileSdk"].toString())

  defaultConfig {
    minSdk = Integer.parseInt(project.extra["minSdk"].toString())
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

dependencies {
  implementation(libs.androidx.core)
  implementation(libs.coroutines.core)
  implementation(libs.timber)

  api(libs.anvil.annotations)

  implementation(libs.dagger)
  kapt(libs.dagger.compiler)
}
