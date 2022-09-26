plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(project(BuildModules.CORE))

    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(AndroidTestDependencies.EXT_JUNIT)
    androidTestImplementation(AndroidTestDependencies.ESPRESSO_CORE)

    // Android Lifecycle Extensions
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.LIFECYCLE_RUNTIME)

    // Navigation
    implementation(Dependencies.NAV_FRAGMENT)
    implementation(Dependencies.NAV_UI)

    // Coroutines
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.KOTLIN_COROUTINES_ADAPTER)

    // Coroutine testing
    testImplementation(TestDependencies.COROUTINES_TEST)
    testImplementation(TestDependencies.MOCKITO_CORE)
    testImplementation(TestDependencies.MOCKITO_INLINE)
    testImplementation(TestDependencies.CORE_TESTING)

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

    // Glide for Images
    implementation(Dependencies.GLIDE)
}