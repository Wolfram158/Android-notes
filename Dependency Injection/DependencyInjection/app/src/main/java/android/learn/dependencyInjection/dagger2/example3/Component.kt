package android.learn.dependencyInjection.dagger2.example3

import dagger.Component

@Component(modules = [ModuleB::class, ModuleA::class])
interface Component {
    fun injectA(a: A)

    fun injectExample3(activity: Example3)
}