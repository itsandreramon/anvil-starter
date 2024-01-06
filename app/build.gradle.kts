plugins {
  alias(libs.plugins.example.android.application)
  alias(libs.plugins.example.android.application.compose)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.anvil)
}

android {
  namespace = "app.example"

  defaultConfig {
    applicationId = "app.example"

    versionCode = 1
    versionName = "1.0"
  }

  buildTypes {
    release {
      isMinifyEnabled = true

      val defaultProguardFile = getDefaultProguardFile("proguard-android-optimize.txt")
      proguardFiles(defaultProguardFile, "proguard-rules.pro")
    }
  }

  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  implementation(project(":core"))
  implementation(project(":ui"))

  implementation(libs.androidx.core)
  implementation(libs.androidx.appcompat)

  implementation(libs.coroutines.core)
  implementation(libs.coroutines.android)

  implementation(libs.timber)

  implementation(libs.dagger)
  kapt(libs.dagger.compiler)

  testImplementation(libs.junit)
  testImplementation(libs.turbine)
  testImplementation(libs.coroutines.test)
}
