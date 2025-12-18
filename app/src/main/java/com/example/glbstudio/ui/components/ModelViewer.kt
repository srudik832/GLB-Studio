package com.example.glbstudio.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.sceneview.Scene
import io.github.sceneview.math.Position
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNodes

@Composable
fun ModelViewer(
    modelUri: String,
    modifier: Modifier = Modifier
) {
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)

    val nodes = rememberNodes {
        val modelInstance = modelLoader.createModelInstance(modelUri)
        modelInstance?.let {
            add(
                ModelNode(
                    modelInstance = it,
                    scaleToUnits = 1.0f,
                    centerOrigin = Position(0f, 0f, 0f)
                )
            )
        }
    }

    Scene(
        modifier = modifier.fillMaxSize(),
        engine = engine,
        modelLoader = modelLoader,
        childNodes = nodes
    )
}
