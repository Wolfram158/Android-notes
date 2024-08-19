package android.learn.jetpackComposePlayground.examples2

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun Example1() {
    val selectedItem = remember {
        mutableIntStateOf(0)
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val fabIsVisible = remember {
        mutableStateOf(true)
    }
    val rememberCoroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            if (fabIsVisible.value) {
                FloatingActionButton(
                    onClick = {
                        rememberCoroutineScope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Snackbar",
                                actionLabel = "Hide",
                                duration = SnackbarDuration.Long
                            )

                            when (result) {
                                SnackbarResult.Dismissed -> {
                                }
                                SnackbarResult.ActionPerformed -> {
                                    fabIsVisible.value = false
                                }
                            }
                        }
                    }
                ) {
                    Text("ABC")
                }
            }
        },
        bottomBar = {
            BottomAppBar {
                val items = listOf(
                    BottomItem.Add,
                    BottomItem.Edit,
                    BottomItem.Info,
                    BottomItem.Search,
                    BottomItem.Email
                )

                items.forEachIndexed { index, bottomItem ->
                    NavigationBarItem(
                        selected = selectedItem.intValue == index,
                        onClick = { selectedItem.intValue = index },
                        icon = {
                            Icon(bottomItem.icon, contentDescription = null) },
                        label = {
                            Text(text = stringResource(id = bottomItem.titleResId))
                        }
                    )
                }
            }

        },
        topBar = {
            TopAppBar(
                title = { Text("TopAppBar") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Gray)
            )
        }
    ) {
        padding -> padding.calculateBottomPadding()
    }
}

@Composable
fun Example2(
    viewModel: Example2ViewModel
) {
    val clicked = viewModel.clicked.observeAsState(0)

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { viewModel.inc() }
    ) {
        Text("Count: ${clicked.value}")
    }
}