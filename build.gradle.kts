plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.anvil) apply false
}

subprojects {
    configurations.configureEach {
        exclude(group = "androidx.appcompat")
        exclude(group = "com.google.android.material", module = "material")
    }
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
    version = libs.versions.gradle.get()
}