package android.learn.weather.presentation

import android.learn.weather.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.learn.weather.databinding.ActivityWeatherListBinding
import android.learn.weather.domain.Weather
import android.learn.weather.presentation.adapters.WeatherListAdapter
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherListActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var adapter: WeatherListAdapter

    private val binding by lazy {
        ActivityWeatherListBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAddLocation.setOnClickListener {
            launchAddFragment()
        }

        initAdapter()

        viewModel = ViewModelProvider(this, viewModelFactory)[WeatherViewModel::class.java]
        viewModel.weatherList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun isLand() = binding.fragmentContainer != null

    private fun launchDetailActivity(code: Int) {
        val intent = WeatherActivity.newIntent(
            this@WeatherListActivity,
            code
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(code: Int) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, WeatherFragment.newInstance(code))
            .addToBackStack(null)
            .commit()
    }

    private fun launchAddFragment() {
        val intent = WeatherActivity.newIntent(
            this@WeatherListActivity,
            "add"
        )
        startActivity(intent)
    }

    private fun setupSwipeListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                lifecycleScope.launch {
                    val item = adapter.currentList[viewHolder.bindingAdapterPosition]
                    if (isLand()) {
                        supportFragmentManager.popBackStack()
                        delay(500)
                    }
                    viewModel.deleteWeather(item.code)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun initAdapter() {
        adapter = WeatherListAdapter()
        adapter.onWeatherClickListener = object : WeatherListAdapter.OnWeatherClickListener {
            override fun onWeatherClick(weather: Weather) {
                if (isLand()) {
                    launchDetailFragment(weather.code)
                } else {
                    launchDetailActivity(weather.code)
                }
            }
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null
        setupSwipeListener(binding.recyclerView)
    }
}