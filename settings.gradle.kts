@file:Suppress("UnstableApiUsage")

pluginManagement {
  includeBuild("plugins")

  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  // Fail if any per-project plugin attempts to source from their own repositories.
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

  repositories {
    google()
    mavenCentral()
  }
}

plugins {
  id("com.gradle.enterprise") version "3.16.1"
}

gradleEnterprise {
  buildScan {
    publishAlways()
    termsOfServiceAgree = "yes"
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
  }
}

rootProject.name = "anvil-starter"

include(":app")
include(":core")
include(":ui")
