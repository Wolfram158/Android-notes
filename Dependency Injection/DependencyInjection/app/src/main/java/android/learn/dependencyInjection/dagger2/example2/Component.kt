package android.learn.dependencyInjection.dagger2.example2

import dagger.Component

@Component
interface Component {
    fun getB(): B

}