package com.example.glbstudio.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.glbstudio.data.Model3D
import com.example.glbstudio.data.ModelManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val modelManager: ModelManager) : ViewModel() {

    // We convert the Flow from ModelManager into a StateFlow for the UI.
    // This ensures the UI always has the latest list of 3D models.
    val models: StateFlow<List<Model3D>> = modelManager.allModels
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addModel(name: String, uri: String) {
        viewModelScope.launch {
            val newModel = Model3D(name = name, fileUri = uri)
            modelManager.insert(newModel)
        }
    }
}

class MainViewModelFactory(private val manager: ModelManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(manager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}