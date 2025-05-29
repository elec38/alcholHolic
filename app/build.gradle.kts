import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.google.services) apply false
//    id("com.google.gms.google-services") version "4.4.2" apply false
//    id("com.android.application")
//    id("com.google.firebase:firebase-firestore-ktx")
    alias(libs.plugins.google.gms.google.services)
    //왜 Plugin id contains invalid char ':' (only ASCII alphanumeric characters, '.', '_' and '-' characters are valid)
//    id("com.google.gms.google-services")
//    id ("com.android.application")
//    id ("kotlin-android")
//    id ("kotlin-android-extensions")
//    kotlin("android.extensions")
}

android {
    namespace = "com.example.c2h5oh"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.c2h5oh"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    dataBinding {
        enable = true
//        isEnabled = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

//    implementation(libs.firebase.firestore.ktx)
    //왜 에러가 날까요
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}