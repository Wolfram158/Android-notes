package android.learn.jetpackComposePlayground.examples3

import android.learn.jetpackComposePlayground.examples3.ui.theme.Example1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.learn.jetpackComposePlayground.examples3.ui.theme.JetpackComposePlaygroundTheme
import androidx.activity.viewModels

class Example1Activity : ComponentActivity() {
    private val viewModel by viewModels<Example1ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposePlaygroundTheme {
                Example1(viewModel = viewModel)
            }
        }
    }
}

