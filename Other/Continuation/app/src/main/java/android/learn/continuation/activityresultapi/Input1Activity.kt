package android.learn.continuation.activityresultapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.learn.continuation.databinding.ActivityInput1Binding

class Input1Activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityInput1Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent().apply {
                putExtra(EXTRA1_KEY, binding.editInput1.text)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        const val EXTRA1_KEY = "input1"

        fun newIntent(context: Context) = Intent(context, Input1Activity::class.java)
    }
}