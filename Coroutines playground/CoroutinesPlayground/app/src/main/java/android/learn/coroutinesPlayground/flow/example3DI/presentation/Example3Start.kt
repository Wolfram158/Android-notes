package android.learn.coroutinesPlayground.flow.example3DI.presentation

import android.content.Context
import android.learn.coroutinesPlayground.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.learn.coroutinesPlayground.databinding.FragmentExample3StartBinding
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Example3Start : Fragment() {
    private lateinit var stringViewModel: StringViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentExample3StartBinding? = null
    private val binding: FragmentExample3StartBinding
        get() = _binding ?: throw RuntimeException("FragmentExample3StartBinding is null")

    private val component by lazy {
        (requireActivity().application as StringApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExample3StartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stringViewModel = ViewModelProvider(this, viewModelFactory)[StringViewModel::class.java]
        Log.d("StringViewModel", "$stringViewModel")

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                stringViewModel.getStrings().collect {
                    binding.strings.text = it.joinToString(separator = System.lineSeparator())
                }
            }
        }

        binding.nextPage.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Example3Next.newInstance())
                .commit()
        }

        binding.buttonAddString.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                stringViewModel.insertString(binding.editAddString.text.toString())
            }
        }

        binding.getCount.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val count = stringViewModel.getCount(binding.editCount.text.toString())
                withContext(Dispatchers.Main) {
                    binding.textCount.text = String.format(
                        "%s: %s",
                        getString(R.string.count),
                        count
                    )
                }
            }
        }

    }

    companion object {
        fun newInstance() =
            Example3Start().apply {
                arguments = Bundle().apply {
                }
            }

    }
}