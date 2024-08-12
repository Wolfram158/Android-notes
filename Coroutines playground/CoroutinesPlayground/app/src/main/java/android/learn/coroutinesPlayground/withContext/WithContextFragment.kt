package android.learn.coroutinesPlayground.withContext

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.learn.coroutinesPlayground.databinding.FragmentWithContextBinding
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import java.lang.RuntimeException

class WithContextFragment : Fragment() {
    private lateinit var viewModel: WithContextViewModel

    private var _binding: FragmentWithContextBinding? = null
    private val binding: FragmentWithContextBinding
        get() = _binding ?: throw RuntimeException("FragmentWithContextBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWithContextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.isVisible = false

        viewModel = ViewModelProvider(this)[WithContextViewModel::class.java]
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is Error -> {
                    Toast.makeText(context, getString(it.id), Toast.LENGTH_LONG).show()
                    binding.progressBar.isVisible = false
                }
                is Progress -> binding.progressBar.isVisible = true
                is Result -> {
                    binding.result.text = it.value
                    binding.progressBar.isVisible = false
                }
            }
        }

        binding.calculate.setOnClickListener {
            viewModel.calculate(
                binding.inputP.text.toString(),
                binding.inputQ.text.toString(),
                binding.inputN.text.toString()
            )
        }
    }

    companion object {
        fun newInstance() =
            WithContextFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }
}