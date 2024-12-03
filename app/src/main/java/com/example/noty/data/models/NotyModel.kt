package com.example.noty.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotyTable")
data class NotyModel(var title: String, var description: String, var time: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1
}
