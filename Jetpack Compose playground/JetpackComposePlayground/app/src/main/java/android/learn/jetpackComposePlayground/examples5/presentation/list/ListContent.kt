package android.learn.jetpackComposePlayground.examples5.presentation.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun ListContent(component: ListComponent) {
    val state by component.model.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                component.onClickGotoAddScreen()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(paddingValues = it)
        ) {
            items(items = state.resultItems) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = it.a, modifier = Modifier.weight(1f))
                    Text(text = it.b, modifier = Modifier.weight(1f))
                    Text(text = it.mod, modifier = Modifier.weight(1f))
                    Text(text = it.result, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}