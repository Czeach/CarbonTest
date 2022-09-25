import Build_gradle.PluginVersions.kotlin_version
import Build_gradle.PluginVersions.nav_version

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

object PluginVersions {
    const val kotlin_version = "1.6.10"
    const val nav_version = "2.4.2"
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.41")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
}