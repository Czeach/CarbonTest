import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

val properties = org.jetbrains.kotlin.konan.properties.Properties()
properties.load(project.rootProject.file("local.properties").reader())

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
    buildTypes{
        debug {
            buildConfigField("String", "API_KEY", "\"" + properties.getProperty("apiKey") + "\"")
        }
    }
    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    // Coroutines
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.KOTLIN_COROUTINES_ADAPTER)

    //Retrofit 2
    implementation(Dependencies.RETROFIT2)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.OK_HTTP3)
    implementation(Dependencies.GSON)

    // Dagger-hilt
    implementation(Dependencies.HILT)
    kapt(AnnotationProcessors.HILT)

    // Room
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.ROOM_KTX)
    kapt(AnnotationProcessors.ROOM)
    testImplementation(TestDependencies.ROOM_TESTING)

}