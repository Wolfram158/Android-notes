package android.learn.coroutinesplayground

import android.learn.coroutinesplayground.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.job.setOnClickListener {
            launchActivity("job")
        }

        binding.deferred.setOnClickListener {
            launchActivity("deferred")
        }
    }

    private fun launchActivity(path: String) {
        val intent = ContainerActivity.newIntent(
            this@MainActivity,
            path
        )
        startActivity(intent)
    }
}