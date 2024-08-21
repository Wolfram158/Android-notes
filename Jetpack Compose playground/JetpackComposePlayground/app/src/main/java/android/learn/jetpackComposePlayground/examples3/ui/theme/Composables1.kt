package android.learn.jetpackComposePlayground.examples3.ui.theme

import android.learn.jetpackComposePlayground.R
import android.learn.jetpackComposePlayground.examples3.Example1ViewModel
import android.learn.jetpackComposePlayground.examples3.domain.Numbers
import android.learn.jetpackComposePlayground.examples3.navigation.AppNavGraph
import android.learn.jetpackComposePlayground.examples3.navigation.rememberNavigationState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState

//@Preview
@Composable
fun Example1(viewModel: Example1ViewModel) {
    val navigationState = rememberNavigationState()

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            BottomAppBar(modifier = Modifier
                .border(width = 2.dp, color = Color.Black),
                containerColor = Color.White
            ) {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRout = navBackStackEntry?.destination?.route

                val items = listOf(
                    BottomItem.Items,
                    BottomItem.Add
                )

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRout == item.screen.route,
                        onClick = { 
                            navigationState.navigateTo(item.screen.route)
                        },
                        icon = {  
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(
                                text = stringResource(id = item.titleResId),
                                color = Color.Black
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            itemsScreenContent = { ListScreen(viewModel, paddingValues) }
        ) {
            AddScreen {
                viewModel.addItem(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ListScreen(viewModel: Example1ViewModel, paddingValues: PaddingValues) {
    val numbers = viewModel.numbers.observeAsState(listOf())
    val target = 0.5f

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = 72.dp,
            start = 16.dp,
            end = 16.dp
        )
    ) {
        items(
            numbers.value,
            key = { it.id }
        ) {
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
            NumberInBox(number = s)
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
    Box(
        modifier = Modifier
            .padding(8.dp)
            .align(Alignment.CenterHorizontally)
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Sum: $number")
    }
}

@Composable
fun AddScreen(addItemCallback: (Numbers) -> Unit) {
    val text1 = rememberSaveable {
        mutableStateOf("")
    }

    val text2 = rememberSaveable {
        mutableStateOf("")
    }

    val text3 = rememberSaveable {
        mutableStateOf("")
    }

    Column {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            InputItem(state = text1, "x")
            InputItem(state = text2, "y")
            InputItem(state = text3, "z")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
            val x = text1.value.toInt()
            val y = text2.value.toInt()
            val z = text3.value.toInt()
            val sum = x + y + z

            addItemCallback(Numbers(0, x, y, z, sum))
        }) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}

@Composable
fun RowScope.InputItem(state: MutableState<String>, hint: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Black)
            .padding(5.dp)
            .weight(1f)
    ) {
        OutlinedTextField(
            modifier = Modifier.background(Color.White),
            value = state.value,
            onValueChange = {
                state.value = it
            },
            placeholder = { Text(hint) },
            textStyle = TextStyle(color = Color.Red)
        )

    }
}