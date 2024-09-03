package android.learn.jetpackComposePlayground.examples5.presentation.list

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

class DefaultListComponent @AssistedInject constructor(
    private val listStoreFactory: ListStoreFactory,
    @Assisted("onGotoAddScreenClicked") private val onGotoAddScreenClicked: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : ListComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore { listStoreFactory.create() }
    private val scope = componentContext.componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    ListStore.Label.GotoAddScreen -> {
                        onGotoAddScreenClicked()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<ListStore.State> = store.stateFlow

    override fun onClickGotoAddScreen() {
        store.accept(ListStore.Intent.GotoAddScreen)
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("onGotoAddScreenClicked") onGotoAddScreenClicked: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultListComponent
    }
}