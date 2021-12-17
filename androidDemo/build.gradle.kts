plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
}

group = "org.ferredoxin.ferredoxinui"
version = "1.0"

val java_version = JavaVersion.VERSION_11

dependencies {
    implementation(project(":common"))
    implementation(project(":qnotified_style"))
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "org.ferredoxin.ferredoxinui.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "0.1"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
    }
    lint {
        isCheckDependencies = true
    }
    compileOptions {
        sourceCompatibility(java_version)
        targetCompatibility(java_version)
    }
    kotlinOptions {
        jvmTarget = java_version.majorVersion
    }
    namespace = "org.ferredoxin.ferredoxinui.android"
}