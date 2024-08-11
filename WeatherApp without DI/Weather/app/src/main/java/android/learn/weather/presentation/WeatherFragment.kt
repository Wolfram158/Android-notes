package android.learn.weather.presentation

import android.learn.weather.R
import android.learn.weather.databinding.FragmentWeatherBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class WeatherFragment : Fragment() {
    private lateinit var viewModel: WeatherViewModel

    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentWeatherBinding is null")

    private var _code: Int? = null
    private val code: Int
        get() = _code ?: throw RuntimeException("Weather was lost")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _code = requireArguments().getInt(PARAM_CODE)

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        viewModel.getWeather(code).observe(viewLifecycleOwner) {
            if (binding.imageView != null) {
                Picasso.get()
                    .load("https:${it.imageUrl}")
                    .into(binding.imageView)
            }
            binding.textWind.text = format(
                resources.getString(R.string.wind),
                it.wind
            )
            binding.textCloud.text = format(
                resources.getString(R.string.cloud),
                it.cloud
            )
            binding.textDewPoint.text = format(
                resources.getString(R.string.dew_point),
                it.dewPoint
            )
            binding.textHumidity.text = format(
                resources.getString(R.string.humidity),
                it.humidity
            )
            binding.textPressure.text = format(
                resources.getString(R.string.atmospheric_pressure),
                (it.pressure.toDouble().div(1.3322))
                    .toString()
            )
            binding.textLastUpdate1.text = format(
                resources.getString(R.string.last_update),
                it.lastUpdated
            )
            binding.textLocation1.text = String.format("%s, %s, %s",
                it.name,
                it.region,
                it.country
            )
            binding.textTemperature1.text = format(
                resources.getString(R.string.temperature),
                it.tempC
            )
            binding.textLocalTime.text = format(
                resources.getString(R.string.local_time),
                it.localtime
            )
        }
    }

    private fun format(left: String, right: String?): String {
        return String.format("%s %s",
            left,
            right
        )
    }

    companion object {
        private const val PARAM_CODE = "code"

        fun newInstance(code: Int) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAM_CODE, code)
                }
            }
    }
}