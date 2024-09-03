package android.learn.jetpackComposePlayground.examples5.presentation.list

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import android.learn.jetpackComposePlayground.examples5.domain.usecase.GetListUseCase
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import android.learn.jetpackComposePlayground.examples5.presentation.list.ListStore.Intent
import android.learn.jetpackComposePlayground.examples5.presentation.list.ListStore.State
import android.learn.jetpackComposePlayground.examples5.presentation.list.ListStore.Label
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ListStore : Store<Intent, State, Label> {
    sealed interface Intent {
        data object GotoAddScreen : Intent
    }

    data class State(
        val resultItems: List<Quadruple>
    ) {
        sealed interface ResultState {
            data class Calculated(
                val a: Int,
                val b: Int,
                val mod: Int,
                val result: Int
            ) : ResultState
        }
    }

    sealed interface Label {
        data object GotoAddScreen : Label
    }

}

class ListStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getListUseCase: GetListUseCase
) {
    fun create(): ListStore =
        object : ListStore, Store<Intent, State, Label> by storeFactory.create(
            name = "ListStore",
            initialState = State(listOf()),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl
        ) {}

    private sealed interface Action {
        data class ListCalculated(val results: List<Quadruple>) : Action
    }

    private sealed interface Msg {
        data class ListCalculated(val results: List<Quadruple>) : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getListUseCase.invoke().collect {
                    dispatch(Action.ListCalculated(it))
                }
            }
        }
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
            when (intent) {
                Intent.GotoAddScreen -> {
                    publish(Label.GotoAddScreen)
                }
            }
        }
    }
}