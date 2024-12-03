package com.example.noty.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    private lateinit var sharedPreferences:SharedPreferences

    fun helper(context: Context){
        sharedPreferences = context.getSharedPreferences("shared",Context.MODE_PRIVATE)
    }

    var isOnboardShown:Boolean
        get()=sharedPreferences.getBoolean("onboard",false)
        set(value) = sharedPreferences.edit().putBoolean("onboard",value).apply()
}