package android.learn.jetpackComposePlayground.examples2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.learn.jetpackComposePlayground.examples2.ui.theme.JetpackComposePlaygroundTheme
import androidx.lifecycle.ViewModelProvider

class Example2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[Example2ViewModel::class.java]

        setContent {
            JetpackComposePlaygroundTheme {
                Example2(viewModel)
            }
        }
    }
}
