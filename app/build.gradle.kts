plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.serialization.plugin)
    id(libs.plugins.navigation.safe.args.plugin.get().pluginId)
}

android {
    namespace = "com.example.itlinktesttask"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.itlinktesttask"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"https://it-link.ru/\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://it-link.ru/\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    /**
     * navigation
     */
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)

    /**
     * fragment
     */
    implementation(libs.fragment)

    /**
     * dagger hilt
     */
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    /**
     * Retrofit
     */
    implementation(libs.retrofit)

    /**
     * okhttp
     */
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    /**
     * kotlinx-serialization
     */
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.converter)
}