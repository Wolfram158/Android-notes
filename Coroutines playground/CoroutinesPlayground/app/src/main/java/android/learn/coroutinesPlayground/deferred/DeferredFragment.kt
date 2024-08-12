package android.learn.coroutinesPlayground.deferred

import android.learn.coroutinesPlayground.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.learn.coroutinesPlayground.databinding.FragmentJobBinding
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DeferredFragment : Fragment() {
    private var _binding: FragmentJobBinding? = null
    private val binding: FragmentJobBinding
        get() = _binding ?: throw RuntimeException("FragmentJobBinding is null")

    private val range = 0..1000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.isVisible = false

        binding.start.setOnClickListener {
            binding.progressBar.isVisible = true
            binding.start.isEnabled = false

            val deferred1 = getDeferred(4000, binding.sum1)
            val deferred2 = getDeferred(6000, binding.sum2)
            val deferred3 = getDeferred(5000, binding.sum3)

            lifecycleScope.launch {
                val sum1 = deferred1.await()
                val sum2 = deferred2.await()
                val sum3 = deferred3.await()

                binding.progressBar.isVisible = false
                binding.start.isEnabled = true

                Toast.makeText(
                    context,
                    String.format("%s %s", getString(R.string.toast_sum), sum1 + sum2 + sum3),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun getDeferred(time: Long, textView: TextView): Deferred<Int> {
        return lifecycleScope.async {
            delay(time)
            val result = range.random()
            textView.text = result.toString()
            result
        }
    }

    companion object {
        fun newInstance() =
            DeferredFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }
}