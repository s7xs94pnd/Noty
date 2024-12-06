// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    //ksp
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
        //safe args
    id("androidx.navigation.safeargs.kotlin") version "2.8.4" apply false
    //fire base
    id("com.google.gms.google-services") version "4.4.2" apply false
}