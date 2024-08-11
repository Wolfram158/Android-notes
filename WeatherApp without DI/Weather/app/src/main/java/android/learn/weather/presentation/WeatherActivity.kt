package android.learn.weather.presentation

import android.content.Context
import android.content.Intent
import android.learn.weather.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.learn.weather.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityWeatherBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val add = intent.getStringExtra(EXTRA_ADD)
            if (add != null) {
                launchAddWeatherFragment()
                return
            }
            val code = intent.getIntExtra(EXTRA_CODE, 0)
            launchWeatherFragment(code)
        }
    }

    private fun launchAddWeatherFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            AddWeatherFragment.newInstance()
        ).commit()
    }

    private fun launchWeatherFragment(code: Int) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            WeatherFragment.newInstance(code)
        ).commit()
    }

    companion object {
        private const val EXTRA_CODE = "code"
        private const val EXTRA_ADD = "add"

        fun newIntent(context: Context, code: Int): Intent {
            val intent = Intent(context, WeatherActivity::class.java)
            intent.putExtra(EXTRA_CODE, code)
            return intent
        }

        fun newIntent(context: Context, add: String): Intent {
            val intent = Intent(context, WeatherActivity::class.java)
            intent.putExtra(EXTRA_ADD, add)
            return intent
        }
    }
}