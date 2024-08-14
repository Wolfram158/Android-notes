package android.learn.dependencyInjection.dagger2.example2

class A {
    private val b = DaggerComponent.create().getB()
}