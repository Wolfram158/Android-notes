package android.learn.coroutinesPlayground.job

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.learn.coroutinesPlayground.databinding.FragmentJobBinding
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JobFragment : Fragment() {
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

            val job1 = getJob(4000, binding.sum1)
            val job2 = getJob(6000, binding.sum2)
            val job3 = getJob(5000, binding.sum3)

            lifecycleScope.launch {
                job1.join()
                job2.join()
                job3.join()

                binding.progressBar.isVisible = false
                binding.start.isEnabled = true
            }
        }
    }

    private fun getJob(time: Long, textView: TextView): Job {
        return lifecycleScope.launch {
            val result = range.random()
            delay(time)
            textView.text = result.toString()
        }
    }

    companion object {
        fun newInstance() =
            JobFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }
}