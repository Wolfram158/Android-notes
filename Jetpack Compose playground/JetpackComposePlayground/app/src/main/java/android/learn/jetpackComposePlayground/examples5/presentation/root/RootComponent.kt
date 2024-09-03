package android.learn.jetpackComposePlayground.examples5.presentation.root

import android.learn.jetpackComposePlayground.examples5.presentation.addition.AdditionComponent
import android.learn.jetpackComposePlayground.examples5.presentation.list.ListComponent
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class List(val component: ListComponent) : Child

        data class Addition(val component: AdditionComponent) : Child
    }
}