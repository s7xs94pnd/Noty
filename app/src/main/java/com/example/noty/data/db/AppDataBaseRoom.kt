package com.example.noty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noty.data.db.daos.NotyDao
import com.example.noty.data.models.NotyModel

@Database(entities = [NotyModel::class], version = 1)
abstract class AppDataBaseRoom : RoomDatabase() {
    abstract fun notyDao():NotyDao
}