package android.learn.dependencyInjection.dagger2.example3

import javax.inject.Inject

class A @Inject constructor(
    val b: B
) {
    init {
        DaggerComponent.create().injectA(this)
    }

    fun getNumber(): Int {
        return 1 + b.getNumber()
    }
}