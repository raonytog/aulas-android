plugins {
    alias(libs.plugins.android.application)
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
}

android {
    namespace = "com.example.ex1"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.ex1"
        minSdk = 31
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation 'androidx.room:room-runtime:2.7.2'
    kapt 'androidx.room:room-compiler:2.7.2'
}