import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

group = "app.example.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
}

dependencies {
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.android.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("exampleAndroidApplication") {
      id = "example.android.application"
      implementationClass = "AndroidApplicationConventionPlugin"
    }

    register("exampleAndroidApplicationCompose") {
      id = "example.android.application.compose"
      implementationClass = "AndroidApplicationComposeConventionPlugin"
    }

    register("exampleAndroidLibrary") {
      id = "example.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }

    register("exampleAndroidLibraryCompose") {
      id = "example.android.library.compose"
      implementationClass = "AndroidLibraryComposeConventionPlugin"
    }
  }
}
