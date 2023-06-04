plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "${MyConfiguration.myApplicationIdConfig}.presentation"
    compileSdk = MyConfiguration.configCompileSdkVersion

    defaultConfig {
        minSdk = MyConfiguration.configMinSdkVersion
        targetSdk = MyConfiguration.configTargetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(MyConfiguration.Modules.DOMAIN))
    implementation(project(MyConfiguration.Modules.MODEL))

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.hilt.navigation)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}