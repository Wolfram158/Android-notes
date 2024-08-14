package android.learn.dependencyInjection.dagger2.example4

import javax.inject.Inject

class A @Inject constructor(
    val b: InterfaceB
) {
    init {
        DaggerComponent.create().injectA(this)
    }

    fun getNumber() = 10 + b.getNumber()
}