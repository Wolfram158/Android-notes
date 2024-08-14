package android.learn.dependencyInjection.dagger2.example5

import javax.inject.Inject

class B @Inject constructor(
    private val number: Int
): InterfaceB {
    override fun getNumber(): Int {
        return number + 3
    }

}