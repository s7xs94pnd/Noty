plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //ksp
    id("com.google.devtools.ksp")
    //Safe Args
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.noty"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.noty"
        minSdk = 28
        targetSdk = 34
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
    buildFeatures{
        viewBinding =true
    }
}

dependencies {
//navigation
    val nav_version = "2.8.4"
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")
    //lottie animations
    val lottieVersion = "3.4.0"
    implementation("com.airbnb.android:lottie:$lottieVersion")
    //Dots tab layout
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    //room
    val room_version = "2.6.1"
    implementation("androidx.room:room-ktx:$room_version")
    //ksp
    ksp("androidx.room:room-compiler:2.5.0")
    //safe Args

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}