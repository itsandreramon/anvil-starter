plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "app.example.base"
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
    implementation(libs.androidx.activity)

    testImplementation(libs.junit)
}