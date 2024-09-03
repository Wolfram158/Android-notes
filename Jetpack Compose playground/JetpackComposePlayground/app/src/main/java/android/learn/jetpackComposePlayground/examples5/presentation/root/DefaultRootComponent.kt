package android.learn.jetpackComposePlayground.examples5.presentation.root

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import android.learn.jetpackComposePlayground.examples5.presentation.addition.DefaultAdditionComponent
import android.learn.jetpackComposePlayground.examples5.presentation.list.DefaultListComponent
import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class DefaultRootComponent @AssistedInject constructor(
    private val listComponentFactory: DefaultListComponent.Factory,
    private val additionComponentFactory: DefaultAdditionComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = null,
        initialConfiguration = Config.List,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            Config.Addition -> {
                val component = additionComponentFactory.create(
                    onClickBack = { navigation.pop() },
                    onClickBackAndExit = { navigation.pop() },
                    quadruple = Quadruple(),
                    componentContext = componentContext
                )
                RootComponent.Child.Addition(component)
            }
            Config.List -> {
                val component = listComponentFactory.create(
                    onGotoAddScreenClicked = {
                        navigation.push(Config.Addition)
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.List(component)
            }
        }
    }

    sealed interface Config : Parcelable {
        @Parcelize
        data object List : Config

        @Parcelize
        data object Addition: Config
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}