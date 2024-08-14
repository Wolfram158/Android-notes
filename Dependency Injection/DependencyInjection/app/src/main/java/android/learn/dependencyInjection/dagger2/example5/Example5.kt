package android.learn.dependencyInjection.dagger2.example5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.learn.dependencyInjection.R
import android.util.Log
import javax.inject.Inject

class Example5 : AppCompatActivity() {
    @Inject
    lateinit var a: A

    private val component = DaggerComponent.builder().number(5).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example4)

        component.injectExample5(this)
        Log.d("Example4", a.getNumber().toString())
    }
}