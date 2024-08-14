package android.learn.dependencyInjection.dagger2.example5

import dagger.BindsInstance
import dagger.Component

@Component(modules = [ModuleA::class, ModuleB::class])
interface Component {
    fun injectA(a: A)

    fun injectExample5(activity: Example5)

    @Component.Builder
    interface ComponentBuilder {
        @BindsInstance
        fun number(number: Int): ComponentBuilder

        fun build(): android.learn.dependencyInjection.dagger2.example5.Component
    }

}