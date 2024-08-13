package android.learn.coroutinesPlayground

import android.learn.coroutinesPlayground.databinding.ActivityMainBinding
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
            launchActivity(Fragments.JOB.name)
        }

        binding.deferred.setOnClickListener {
            launchActivity(Fragments.DEFERRED.name)
        }

        binding.withContext.setOnClickListener {
            launchActivity(Fragments.WITH_CONTEXT.name)
        }

        binding.example3Flow.setOnClickListener {
            launchActivity(Fragments.EXAMPLE3.name)
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