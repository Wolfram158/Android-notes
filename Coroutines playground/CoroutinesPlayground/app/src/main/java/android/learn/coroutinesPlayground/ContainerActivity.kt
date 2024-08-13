package android.learn.coroutinesPlayground

import android.content.Context
import android.content.Intent
import android.learn.coroutinesPlayground.deferred.DeferredFragment
import android.learn.coroutinesPlayground.job.JobFragment
import android.learn.coroutinesPlayground.databinding.ActivityContainerBinding
import android.learn.coroutinesPlayground.flow.example3.presentation.Example3Start
import android.learn.coroutinesPlayground.withContext.WithContextFragment
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
                Fragments.JOB.name -> launchFragment(JobFragment.newInstance())
                Fragments.DEFERRED.name -> launchFragment(DeferredFragment.newInstance())
                Fragments.WITH_CONTEXT.name -> launchFragment(WithContextFragment.newInstance())
                Fragments.EXAMPLE3.name -> launchFragment(Example3Start.newInstance())
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