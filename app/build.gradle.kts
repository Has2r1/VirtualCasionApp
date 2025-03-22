plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.mainscreen"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mainscreen"
        minSdk = 26
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


    buildFeatures {
        compose = true
    }
}

dependencies {
    coreLibraryDesugaring (libs.desugar.jdk.libs)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.ui)
    implementation(libs.androidx.material)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx.v262)
    implementation(libs.androidx.activity.compose.v182)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.foundation.v154)
    implementation(libs.androidx.ui.v154)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v287)
    implementation(libs.androidx.activity.compose.v172)

    implementation (libs.androidx.runtime)
    implementation (libs.androidx.ui.v150)
    implementation (libs.androidx.material.v150)
    implementation (libs.androidx.activity.compose.v1101)
    implementation (libs.androidx.lifecycle.viewmodel.ktx.v287)
    implementation (libs.androidx.lifecycle.runtime.ktx.v287)
    implementation (libs.androidx.lifecycle.runtime.compose)
    implementation (libs.androidx.lifecycle.runtime.compose.v287)
    implementation (libs.gson)

    implementation(libs.retrofit2.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.okhttp)

}