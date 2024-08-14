package android.learn.dependencyInjection.dagger2.example3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.learn.dependencyInjection.R
import android.util.Log
import javax.inject.Inject

class Example3 : AppCompatActivity() {
    @Inject
    lateinit var a: A

    private val component = DaggerComponent.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example3)

        component.injectExample3(this)
        Log.d("Example3", a.getNumber().toString())
    }
}