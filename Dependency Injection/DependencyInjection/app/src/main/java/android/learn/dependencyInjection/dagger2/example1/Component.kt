package android.learn.dependencyInjection.dagger2.example1

import dagger.Component

@Component
interface Component {
    fun inject(a: A)

}