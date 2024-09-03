package android.learn.jetpackComposePlayground.examples5.presentation.addition

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import android.learn.jetpackComposePlayground.examples5.presentation.extensions.componentScope
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultAdditionComponent @AssistedInject constructor(
    private val storeFactory: AdditionStoreFactory,
    @Assisted("onClickBack") private val onBackClicked: () -> Unit,
    @Assisted("onClickBackAndExit") private val onBackAndExitClicked: () -> Unit,
    @Assisted("quadruple") private val quadruple: Quadruple,
    @Assisted("componentContext") componentContext: ComponentContext
) : AdditionComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore { storeFactory.create(quadruple) }
    private val scope = componentScope()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<AdditionStore.State> = store.stateFlow

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    AdditionStore.Label.ClickAddAndExit -> {
                        onBackAndExitClicked()
                    }

                    AdditionStore.Label.ClickBack -> {
                        onBackClicked()
                    }
                }
            }
        }
    }

    override fun onClickAddAndExit() {
        store.accept(AdditionStore.Intent.ClickAddAndExit)
    }

    override fun onClickCalculate() {
        store.accept(AdditionStore.Intent.ClickCalculate)
    }

    override fun onClickBack() {
        store.accept(AdditionStore.Intent.ClickBack)
    }

    override fun changeQuadruple(quadruple: Quadruple) {
        store.accept(AdditionStore.Intent.ChangeQuadruple(quadruple))
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("onClickBack") onClickBack: () -> Unit,
            @Assisted("onClickBackAndExit") onClickBackAndExit: () -> Unit,
            @Assisted("quadruple") quadruple: Quadruple,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultAdditionComponent
    }
}