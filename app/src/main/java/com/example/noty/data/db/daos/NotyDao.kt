package com.example.noty.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noty.data.models.NotyModel

@Dao
interface NotyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notyModel:NotyModel)

    @Query("SELECT * FROM NotyTable")
    fun getAll():LiveData<List<NotyModel>>
}