package android.learn.jetpackComposePlayground.examples3

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Example1(viewModel: Example1ViewModel) {
    val numbers = viewModel.numbers.observeAsState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(
            numbers.value!!,
            key = { it.id }
        ) {
            val target = 0.5f
            val dismissState = rememberSwipeToDismissBoxState()
            if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart &&
                dismissState.progress > target)
            {
                viewModel.removeItem(it)
            }

            SwipeToDismissBox(
                modifier = Modifier
                    .animateItemPlacement(
                        spring(stiffness = Spring.StiffnessVeryLow)
                    ),
                state = dismissState,
                backgroundContent = {},
                enableDismissFromStartToEnd = false
            ) {
                ListItem(x = it.x, y = it.y, z = it.z, s = it.sum)
            }
        }

    }
}

@Composable
fun ListItem(x: Int, y: Int, z: Int, s: Int) {
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        border = BorderStroke(4.dp, color = Color.Black)
    ) {
        Column {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                NumberInBox(number = x)
                NumberInBox(number = y)
                NumberInBox(number = z)
            }
            NumberInBox(s)
        }
    }
}

@Composable
fun RowScope.NumberInBox(number: Int) {
    Box(
        modifier = Modifier
            .weight(1f)
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = number.toString())
    }
}

@Composable
fun ColumnScope.NumberInBox(number: Int) {
    Box(modifier = Modifier
        .padding(8.dp)
        .align(Alignment.CenterHorizontally)
        .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Sum: $number")
    }
}