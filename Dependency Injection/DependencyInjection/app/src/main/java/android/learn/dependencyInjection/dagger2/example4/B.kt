package android.learn.dependencyInjection.dagger2.example4

import javax.inject.Inject

class B @Inject constructor(): InterfaceB {
    override fun getNumber(): Int {
        return 3
    }

}