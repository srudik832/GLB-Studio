package com.example.glbstudio.data

import kotlinx.coroutines.flow.Flow

class ModelManager(private val appDao: AppDao) {

    // This is NOT a suspend call, so it can be initialized here.
    val allModels: Flow<List<Model3D>> = appDao.getAllModels()

    suspend fun insert(model: Model3D) {
        appDao.insertModel(model)
    }
}