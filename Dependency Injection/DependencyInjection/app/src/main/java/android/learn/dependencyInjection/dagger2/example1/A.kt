package android.learn.dependencyInjection.dagger2.example1

import javax.inject.Inject

class A {
    @Inject
    lateinit var b: B

    init {
        DaggerComponent.create().inject(this)
    }
}