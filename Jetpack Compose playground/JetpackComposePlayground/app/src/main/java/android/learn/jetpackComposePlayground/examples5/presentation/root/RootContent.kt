package android.learn.jetpackComposePlayground.examples5.presentation.root

import android.learn.jetpackComposePlayground.examples5.presentation.addition.AdditionContent
import android.learn.jetpackComposePlayground.examples5.presentation.list.ListContent
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children

@Composable
fun RootContent(component: RootComponent) {
    Children(stack = component.stack) {
        when (val instance = it.instance) {
            is RootComponent.Child.Addition -> {
                AdditionContent(component = instance.component)
            }
            is RootComponent.Child.List -> {
                ListContent(component = instance.component)
            }
        }
    }
}