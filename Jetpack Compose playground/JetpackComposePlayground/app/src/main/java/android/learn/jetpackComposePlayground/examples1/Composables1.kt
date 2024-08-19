package android.learn.jetpackComposePlayground.examples1

import android.learn.jetpackComposePlayground.R
import android.learn.jetpackComposePlayground.ui.theme.JetpackComposePlaygroundTheme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

//@Preview
@Composable
fun Example1() {
    Text("ABC", color = Color.Red)
}

//@Preview
@Composable
fun Example2() {
    Text("ABC", color = Color.Red)
    Text("ABC", color = Color.Red)
}

//@Preview
@Composable
fun Example3() {
    Column {
        Text("ABC", color = Color.Red)
        Text("ABC", color = Color.Red)
    }
}

//@Preview
@Composable
fun Example4() {
    Column {
        repeat(5) {
           Text("ABC", color = Color.Green)
       }
    }
}

@Composable
fun Example5(mod: Int, res: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        for (i in 0 until mod) {
            Row(modifier = Modifier.weight(1f)) {
                for (j in 0 until mod) {
                    val color = if ((i * i + j * j) % mod == res) {
                        Color.Green
                    } else {
                        Color.Blue
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .background(color)
                            .border(width = 1.dp, color = Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("${(i * i + j * j) % mod}", color = Color.Red)
                    }
                }
            }
        }
    }
}

//@Preview
@Composable
fun Example5Preview() {
    Example5(mod = 11, res = 10)
}

@Composable
fun Example6Template(darkTheme: Boolean) {
    JetpackComposePlaygroundTheme(darkTheme = darkTheme) {
        Card(
            modifier = Modifier.height(130.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp
            ),
            border = BorderStroke(
                width = 4.dp,
                MaterialTheme.colorScheme.onBackground
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.weight(1f),
                    contentAlignment = Alignment.Center) {
                    Image(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = ""
                    )
                }
                Example6Helper2 {
                    Example6Helper1("1234", "A")
                }
                Example6Helper2 {
                    Example6Helper1("BCDEFGHIJ", "KL")
                }
            }
        }
    }
}

//@Preview
@Composable
fun Example6Light() {
    Example6Template(darkTheme = false)
}

//@Preview
@Composable
fun Example6Dark() {
    Example6Template(darkTheme = true)
}

@Composable
fun Example6Helper1(str1: String, str2: String) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.weight(1f),
            contentAlignment = Alignment.Center) {
            Text(str1)
        }
        Box(Modifier.weight(1f),
            contentAlignment = Alignment.Center) {
            Text(str2)
        }
    }
}

@Composable
fun RowScope.Example6Helper2(content: @Composable () -> Unit) {
    Box(Modifier.weight(1f),
        contentAlignment = Alignment.Center) {
        content.invoke()
    }
}

//@Preview
@Composable
fun Example7_1() {
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Blue)
    ) {
        Box(
            Modifier
                .background(Color.Red)
                .padding(8.dp)
        ) {
            Text("123")
        }
    }
}

//@Preview
@Composable
fun Example7_2() {
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Blue)
    ) {
        Box(
            Modifier
                .padding(8.dp)
                .background(Color.Red)
        ) {
            Text("123")
        }
    }
}