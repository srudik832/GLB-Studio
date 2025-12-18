package com.example.glbstudio.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "models")
data class Model3D(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val fileUri: String
)