package android.learn.weather.presentation

import android.content.Context
import android.learn.weather.R
import android.learn.weather.data.network.model.WeatherDto
import android.learn.weather.databinding.FragmentAddWeatherBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddWeatherFragment : Fragment() {
    private lateinit var viewModel: WeatherViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var weatherDto: WeatherDto

    private var _binding: FragmentAddWeatherBinding? = null
    private val binding: FragmentAddWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentAddWeatherBinding is null")

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[WeatherViewModel::class.java]
        binding.imageButton.setOnClickListener {
            onClickFindWeather()
        }
        binding.addLocation.setOnClickListener {
            onClickAddLocation()
        }
    }

    private fun onClickFindWeather() {
        lifecycleScope.launch {
            try {
                weatherDto = viewModel.findWeather(binding.editLocation.text.toString())
                binding.textNameOfLocation.text = weatherDto.locationDetailsDto.name
                binding.editLocation.setText(resources.getText(R.string.empty))
            } catch (_: Exception) {
                Toast.makeText(
                    context,
                    resources.getString(R.string.no_internet_or_query),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onClickAddLocation() {
        lifecycleScope.launch {
            try {
                viewModel.insertWeather(weatherDto)
                Toast.makeText(
                    context,
                    resources.getString(R.string.add_success),
                    Toast.LENGTH_SHORT
                ).show()
            } catch (_: Exception) {
                Toast.makeText(
                    context,
                    resources.getString(R.string.add_failure),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        fun newInstance() = AddWeatherFragment()

    }

}