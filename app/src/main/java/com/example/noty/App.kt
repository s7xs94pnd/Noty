package com.example.noty

import android.app.Application
import android.content.SharedPreferences
import com.example.noty.ui.utils.PreferenceHelper

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        var sharedPreferences = PreferenceHelper()
        sharedPreferences.helper(this)

    }
}