package android.learn.jetpackComposePlayground.examples5.presentation.addition

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import android.learn.jetpackComposePlayground.examples5.domain.usecase.AddToListUseCase
import android.learn.jetpackComposePlayground.examples5.domain.usecase.CalculateUseCase
import android.learn.jetpackComposePlayground.examples5.presentation.addition.AdditionStore.Intent
import android.learn.jetpackComposePlayground.examples5.presentation.addition.AdditionStore.State
import android.learn.jetpackComposePlayground.examples5.presentation.addition.AdditionStore.Label
import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

interface AdditionStore : Store<Intent, State, Label> {
    sealed interface Intent {
        data object ClickBack : Intent

        data object ClickCalculate : Intent

        data object ClickAddAndExit : Intent

        data class ChangeQuadruple(val quadruple: Quadruple) : Intent
    }

    data class State(
        val quadruple: Quadruple,
        val resultState: ResultState
    ) {
        sealed interface ResultState {
            data object Initial : ResultState

            data class Calculated(val quadruple: Quadruple) : ResultState

            data object Calculating : ResultState

            data object Error : ResultState
        }
    }

    sealed interface Label {
        data object ClickBack : Label

        data object ClickAddAndExit : Label
    }
}

class AdditionStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val calculateUseCase: CalculateUseCase,
    private val addToListUseCase: AddToListUseCase
) {
    fun create(quadruple: Quadruple): AdditionStore =
        object : AdditionStore, Store<Intent, State, Label> by storeFactory.create(
            name = "AdditionStore",
            initialState = State(quadruple, State.ResultState.Initial),
            bootstrapper = BootstrapperImpl(quadruple),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class ResultCalculated(val quadruple: Quadruple) : Action

        data object Error : Action

        data object Calculating : Action
    }

    private sealed interface Msg {
        data class ResultCalculated(val quadruple: Quadruple) : Msg

        data object Error : Msg

        data object Calculating : Msg

        data class ChangeQuadruple(val quadruple: Quadruple) : Msg
    }

    private inner class BootstrapperImpl(
        private val quadruple: Quadruple
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
//            scope.launch {
//                dispatch(Action.Calculating)
//                try {
//                    val result = calculateUseCase(quadruple)
//                    dispatch(Action.ResultCalculated(result))
//                } catch (e: Exception) {
//                    Log.d("MVIKotlin", "Error occurred")
//                    dispatch(Action.Error)
//                }
//            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        private var job: Job? = null

        override fun executeIntent(intent: Intent) {
            when (intent) {
                Intent.ClickAddAndExit -> {
                    publish(Label.ClickAddAndExit)
                }

                Intent.ClickBack -> {
                    publish(Label.ClickBack)
                    scope.launch {
                        addToListUseCase(state().quadruple)
                    }
                }

                Intent.ClickCalculate -> {
                    Log.d("MVIKotlin", state().quadruple.toString())
//                    job?.cancel()
                    job = scope.launch {
                        dispatch(Msg.Calculating)
                        try {
                            Log.d("MVIKotlin", "Before calculating")
                            val quadruple = calculateUseCase(state().quadruple)
                            dispatch(Msg.ResultCalculated(quadruple))
                        } catch (e: Exception) {
                            Log.d("MVIKotlin", e.toString())
                            dispatch(Msg.Error)
                        }
                    }
                }

                is Intent.ChangeQuadruple -> {
                    dispatch(Msg.ChangeQuadruple(intent.quadruple))
                }
            }
        }

        override fun executeAction(action: Action) {
            when (action) {
                Action.Calculating -> {
                    dispatch(Msg.Calculating)
                }

                Action.Error -> {
                    dispatch(Msg.Error)
                }

                is Action.ResultCalculated -> {
                    Log.d("MVIKotlin", action.quadruple.toString())
                    dispatch(Msg.ResultCalculated(action.quadruple))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State {
            return when (msg) {
                Msg.Calculating -> {
                    copy(resultState = State.ResultState.Calculating)
                }

                Msg.Error -> {
                    copy(resultState = State.ResultState.Error)
                }

                is Msg.ResultCalculated -> {
                    Log.d("MVIKotlin", msg.quadruple.toString())
                    copy(
                        quadruple = msg.quadruple,
                        resultState = State.ResultState.Calculated(msg.quadruple)
                    )
                }

                is Msg.ChangeQuadruple -> {
                    copy(quadruple = msg.quadruple)
                }
            }
        }

    }
}