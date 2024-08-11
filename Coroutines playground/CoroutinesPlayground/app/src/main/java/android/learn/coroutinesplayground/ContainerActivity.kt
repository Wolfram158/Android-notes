package android.learn.coroutinesplayground

import android.content.Context
import android.content.Intent
import android.learn.coroutinesplayground.databinding.ActivityContainerBinding
import android.learn.coroutinesplayground.deferred.DeferredFragment
import android.learn.coroutinesplayground.job.JobFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class ContainerActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityContainerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_PATH)) {
            finish()
            return
        }

        if (savedInstanceState == null) {
            val fragmentKey = intent.getStringExtra(EXTRA_PATH)
            when (fragmentKey) {
                "job" -> launchFragment(JobFragment.newInstance())
                "deferred" -> launchFragment(DeferredFragment.newInstance())
            }
        }

    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    companion object {
        private const val EXTRA_PATH = "path"

        fun newIntent(context: Context, path: String): Intent {
            val intent = Intent(context, ContainerActivity::class.java)
            intent.putExtra(EXTRA_PATH, path)
            return intent
        }
    }
}