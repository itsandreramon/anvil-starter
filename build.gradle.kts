import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.anvil) apply false
    alias(libs.plugins.spotless)
}

configure<SpotlessExtension> {
    kotlin {
        target("**/src/**/*.kt")
        ktlint().editorConfigOverride(
            mapOf(
                "ktlint_standard_filename" to "disabled",
                "ktlint_standard_no-empty-first-line-in-class-body" to "disabled",
                "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
            ),
        )
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

allprojects {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            // allWarningsAsErrors = true
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }
}

subprojects {
    configurations.configureEach {
        exclude(group = "com.google.android.material", module = "material")
    }
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
    version = libs.versions.gradle.get()
}
