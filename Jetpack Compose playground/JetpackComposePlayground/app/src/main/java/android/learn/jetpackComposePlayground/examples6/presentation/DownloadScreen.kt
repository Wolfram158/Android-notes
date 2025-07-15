package android.learn.jetpackComposePlayground.examples6.presentation

import android.learn.jetpackComposePlayground.examples6.presentation.model.Action
import android.learn.jetpackComposePlayground.examples6.presentation.model.DownloadState
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val LOG_TAG = "Probability measurement"

@Composable
@Preview
fun DownloadScreen(downloadViewModel: DownloadViewModel = viewModel()) {
    val state = downloadViewModel.state.collectAsState(DownloadState())
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    var fail = 0
    var success = 0

    Scaffold(modifier = Modifier.fillMaxSize()) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (state.value.isLoading) {
                true -> {
                    CircularProgressIndicator()
                }

                false -> {
                    state.value.result?.let { text ->
                        Text(text = text)
                        success++
                        Log.e(LOG_TAG, "$success success")
                    }
                    state.value.error?.let { text ->
                        Toast.makeText(LocalContext.current, text, Toast.LENGTH_LONG).show()
                        Icon(
                            imageVector = Icons.Outlined.Warning,
                            modifier = Modifier.size(50.dp),
                            tint = Color.Red,
                            contentDescription = "Warning"
                        )
                        fail++
                        Log.e(LOG_TAG, "$fail fail")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    downloadViewModel.action(Action.Download)
                }, enabled = !state.value.isLoading,
                interactionSource = interactionSource
            ) {
                Text(text = "Click")
            }
        }
    }

    LaunchedEffect(Unit) {
        launch {
            repeat(100) {
                val press = PressInteraction.Press(Offset.Zero)
                interactionSource.emit(press)
                downloadViewModel.action(Action.Download)
                delay(6000)
                interactionSource.emit(PressInteraction.Release(press))
            }
        }
    }
}