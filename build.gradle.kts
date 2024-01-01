import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.anvil) apply false
}

allprojects {
    tasks.withType<KotlinCompilationTask<*>>().configureEach {
        compilerOptions {
            allWarningsAsErrors = true

            freeCompilerArgs.addAll(
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
            )
        }
    }
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