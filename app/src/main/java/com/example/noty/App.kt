package com.example.noty

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.noty.data.db.AppDataBaseRoom
import com.example.noty.data.models.NotyModel
import com.example.noty.utils.PreferenceHelper


class App : Application() {

    companion object {
        // типа статичная переменая
        var appDataBaseRoom: AppDataBaseRoom? = null
    }

    override fun onCreate() {
        super.onCreate()
        var sharedPreferences = PreferenceHelper()
        sharedPreferences.helper(this)
        dataBaseRoomExist()
    }

    // тут проверяется было ли создана база Данных Room, если нет то она создается
    private fun dataBaseRoomExist(): AppDataBaseRoom? {
        if (appDataBaseRoom == null) {
            appDataBaseRoom = Room.databaseBuilder(applicationContext, AppDataBaseRoom::class.java, "noty.database")
                    .fallbackToDestructiveMigrationFrom().allowMainThreadQueries().build()
            }
        return appDataBaseRoom
        }
    }
// одно и тоже
/*if (appDataBaseRoom == null) {
    val context = applicationContext
    val databaseBuilder = Room.databaseBuilder(context, AppDataBaseRoom::class.java, "noty.database")
    databaseBuilder.fallbackToDestructiveMigrationFrom()
    databaseBuilder.allowMainThreadQueries()
    appDataBaseRoom = databaseBuilder.build()
}*/
