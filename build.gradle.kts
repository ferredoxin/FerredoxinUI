buildscript {
    repositories {
        maven("https://maven.aliyun.com/nexus/content/groups/public/")
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        val kotlin_version = "1.5.31"
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.android.tools.build:gradle:7.0.4")
    }
}

allprojects {
    afterEvaluate {
        // Remove log pollution until Android support in KMP improves.
        project.extensions.findByType<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>()?.let { kmpExt ->
            kmpExt.sourceSets.removeAll { arrayOf("androidAndroidTestRelease").contains(it.name) }
        }
    }
}

group = "org.ferredoxin.ferredoxinui"
version = "1.0"