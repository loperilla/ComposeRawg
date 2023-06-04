import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    namespace = "${MyConfiguration.myApplicationIdConfig}.datasource"
    compileSdk = MyConfiguration.configCompileSdkVersion

    defaultConfig {
        minSdk = MyConfiguration.configMinSdkVersion
        targetSdk = MyConfiguration.configTargetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val key: String = gradleLocalProperties(rootDir).getProperty("API_KEY")
        buildConfigField(
            "String",
            "API_KEY",
            key
        )
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
}

dependencies {
    implementation(project(MyConfiguration.MAP_MODULES.MODEL))

    //Datastore
    implementation(libs.datastore)
    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //ktor
    implementation(libs.bundles.ktor)
}