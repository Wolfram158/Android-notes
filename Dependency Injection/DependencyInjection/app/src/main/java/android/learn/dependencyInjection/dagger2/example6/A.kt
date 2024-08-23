package android.learn.dependencyInjection.dagger2.example6

import javax.inject.Inject
import javax.inject.Named

class A @Inject constructor(
    @Named("x") val x: Int,
    @Named("y") val y: Int
) {
    fun sum() = x + y
}