package android.learn.continuation.activityresultapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.learn.continuation.databinding.ActivityMain1Binding
import androidx.activity.result.contract.ActivityResultContracts

class Main1Activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMain1Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val input1Contract = ActivityResultContracts.StartActivityForResult()
        val launcherInput1 = registerForActivityResult(input1Contract) {
            if (it.resultCode == RESULT_OK) {
                val str = it.data?.getCharSequenceExtra(Input1Activity.EXTRA1_KEY)
                binding.textInput1.text = str
            }
        }

        val imageContract = ActivityResultContracts.GetContent()
        val launcherImage = registerForActivityResult(imageContract) {
            binding.imageView.setImageURI(it)
        }

        binding.buttonInput1.setOnClickListener {
            launcherInput1.launch(Input1Activity.newIntent(this))
        }

        binding.buttonImage.setOnClickListener {
            launcherImage.launch("image/*")
        }

        val pdfContract = ActivityResultContracts.OpenDocument()
        val pdfLauncher = registerForActivityResult(pdfContract) {
        }
        pdfLauncher.launch(arrayOf("application/pdf"))
    }
}