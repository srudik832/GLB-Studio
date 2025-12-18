package com.example.glbstudio

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.sceneview.Scene
import io.github.sceneview.math.Position
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.model.Model

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    GLBFilePickerScreen()
                }
            }
        }
    }
}

@Composable
fun GLBFilePickerScreen() {
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)

    // Using a list for nodes is the most stable approach for the Scene composable
    val nodes = remember { mutableStateListOf<ModelNode>() }
    var isLoading by remember { mutableStateOf(false) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { selectedUri ->
            isLoading = true

            // Explicitly typing the callback to 'Model?'
            modelLoader.loadModelAsync(selectedUri.toString()) { loadedModel: Model? ->
                loadedModel?.let { m ->
                    nodes.clear()
                    val newNode = ModelNode(
                        modelInstance = m.instance,
                        scaleToUnits = 1.0f
                    ).apply {
                        position = Position(z = -2.0f)
                    }
                    nodes.add(newNode)
                }
                isLoading = false
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // We provide all parameters explicitly to avoid 'Cannot infer type'
        Scene(
            modifier = Modifier.fillMaxSize(),
            engine = engine,
            modelLoader = modelLoader,
            childNodes = nodes,
            onViewCreated = null, // Setting to null to bypass the Function1/Function2 error
            onFrame = null        // Setting to null as well for maximum stability
        )

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        Button(
            onClick = { filePickerLauncher.launch("*/*") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ) {
            Text(text = "Pick .GLB Model")
        }
    }
}