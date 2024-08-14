package android.learn.dependencyInjection.dagger2.example4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.learn.dependencyInjection.R
import android.util.Log
import javax.inject.Inject

class Example4 : AppCompatActivity() {
    @Inject
    lateinit var a: A

    private val component = DaggerComponent.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example4)

        component.injectExample4(this)
        Log.d("Example4", a.getNumber().toString())
    }
}