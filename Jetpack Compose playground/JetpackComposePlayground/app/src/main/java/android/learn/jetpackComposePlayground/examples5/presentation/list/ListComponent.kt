package android.learn.jetpackComposePlayground.examples5.presentation.list

import kotlinx.coroutines.flow.StateFlow

interface ListComponent {
    val model: StateFlow<ListStore.State>

    fun onClickGotoAddScreen()
}