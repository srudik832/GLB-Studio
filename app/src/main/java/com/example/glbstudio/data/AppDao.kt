package com.example.glbstudio.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    // Room returns Flow without needing the 'suspend' keyword.
    @Query("SELECT * FROM models")
    fun getAllModels(): Flow<List<Model3D>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModel(model: Model3D)
}