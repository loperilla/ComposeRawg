@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    compileSdk = MyConfiguration.configCompileSdkVersion
    namespace = MyConfiguration.myApplicationIdConfig

    defaultConfig {
        applicationId = MyConfiguration.myApplicationIdConfig
        minSdk = MyConfiguration.configMinSdkVersion
        targetSdk = MyConfiguration.configTargetSdkVersion
        versionCode = MyConfiguration.configVersionCode
        versionName = MyConfiguration.configVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.lifecycle.compose.runtime)
    implementation(libs.lifecycle.compose.viewmodel)

    implementation(project(MyConfiguration.Modules.COREUI))
    implementation(project(MyConfiguration.Modules.DOMAIN))
    implementation(project(MyConfiguration.Modules.MODEL))
    implementation(project(MyConfiguration.Modules.PRESENTATION))

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.hilt.navigation)
    implementation(libs.compose.navigation)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}