package com.example.noty.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotyTable")
data class NotyModel(val title: String, val description: String, val time: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
