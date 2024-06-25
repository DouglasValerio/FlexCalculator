package info.codementor.flexcalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import info.codementor.flexcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel = ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)

        binding.evaluateBestFuelButton.setOnClickListener { viewModel.evaluateConsumption(
            binding.baseFuelInputField.text.toString(),
            binding.compareFuelInputField.text.toString(),
            binding.baseFuelPriceInputField.text.toString(),
            binding.compareFuelPriceInputField.text.toString(),
        )
            binding.resultTextView.text = viewModel.fuel.toString()
        }

    }
}