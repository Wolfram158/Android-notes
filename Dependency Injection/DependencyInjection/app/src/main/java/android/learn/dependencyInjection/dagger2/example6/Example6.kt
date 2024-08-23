package android.learn.dependencyInjection.dagger2.example6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.learn.dependencyInjection.R
import android.util.Log
import javax.inject.Inject

class Example6 : AppCompatActivity() {
    @Inject
    lateinit var a: A

    private val component = DaggerExample6Component.factory().create(5, 11)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example4)

        component.inject(this)
        Log.d("Example6", a.sum().toString())
    }
}