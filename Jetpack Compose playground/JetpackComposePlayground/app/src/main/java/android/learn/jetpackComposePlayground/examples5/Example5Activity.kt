package android.learn.jetpackComposePlayground.examples5

import android.learn.jetpackComposePlayground.examples5.presentation.root.DefaultRootComponent
import android.learn.jetpackComposePlayground.examples5.presentation.root.RootContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import javax.inject.Inject

class Example5Activity : ComponentActivity() {
    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).applicationComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContent {
            RootContent(component = rootComponentFactory.create(defaultComponentContext()))
        }
    }
}