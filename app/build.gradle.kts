import java.util.Properties

plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
}

android {

    compileSdk = AndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = AndroidConfig.APP_ID
        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val properties = initProperties()
            storeFile = File(properties["storeFilePath"]?.toString() ?: "")
            storePassword = properties["storePassword"]?.toString()
            keyAlias = properties["keyAlias"]?.toString()
            keyPassword = properties["keyPassword"]?.toString()
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    androidX()
    coroutines()
    hilt()
    material()
    splashscreen()
    navigation()
    implementationModules(Module.ALL_FEATURES)
}

fun initProperties(): Properties {
    val properties = Properties()
    val keyFilePropertiesPath = project.rootProject.file("key.properties").path
    properties.load(File(keyFilePropertiesPath).inputStream())
    return properties
}