import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.parcelize) apply false
  alias(libs.plugins.kotlin.kapt) apply false
  alias(libs.plugins.anvil) apply false
  alias(libs.plugins.detekt)
  alias(libs.plugins.spotless)
}

subprojects {
  apply {
    plugin(rootProject.libs.plugins.detekt.get().pluginId)
    plugin(rootProject.libs.plugins.spotless.get().pluginId)
  }

  detekt {
    detekt {
      val configFile = rootProject.file("detekt.yml")
      config.setFrom(configFile)
    }
  }

  configure<SpotlessExtension> {
    kotlin {
      target("src/**/*.kt")
      ktlint().editorConfigOverride(
        mapOf(
          "ktlint_standard_filename" to "disabled",
          "ktlint_standard_no-empty-first-line-in-class-body" to "disabled",
          "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
        ),
      )
    }

    kotlinGradle {
      target("**/*.kts")
      ktlint()
    }
  }

  configurations.configureEach {
    exclude(group = "com.google.android.material", module = "material")
  }
}

tasks.wrapper {
  distributionType = Wrapper.DistributionType.ALL
  version = libs.versions.gradle.get()
}
