package com.example.noty.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noty.data.models.NotyModel

@Dao
interface NotyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notyModel:NotyModel)

    @Query("SELECT * FROM NotyTable")
    fun getAll():LiveData<List<NotyModel>>

    @Query("SELECT * FROM NotyTable WHERE id =:id")
    fun getById(id:Int):NotyModel

    @Delete
    fun delete(notyModel: NotyModel)

    @Update
    fun update(notyModel: NotyModel)
}