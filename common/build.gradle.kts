import org.jetbrains.compose.compose

plugins {
    id("org.jetbrains.compose")
    kotlin("multiplatform")
    id("com.android.library")
}

group = "org.ferredoxin.ferredoxinui"
version = "1.0"

val java_version = JavaVersion.VERSION_11

kotlin {
    android {
        compilations.all {
            kotlinOptions.jvmTarget = java_version.majorVersion
        }
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = java_version.majorVersion
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.preview)
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.4.0")
                api("androidx.appcompat:appcompat:1.4.0")
                api("androidx.core:core-ktx:1.7.0")
                api("com.google.android.material:material:1.4.0")

                val lifecycleVersion = "2.4.0-alpha03"
                api("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
                api("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.desktop.common)
            }
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility(java_version)
        targetCompatibility(java_version)
    }
    namespace = "org.ferredoxin.ferredoxinui.common"

}

java {
    sourceCompatibility = java_version
    targetCompatibility = java_version
}