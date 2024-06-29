package info.codementor.flexcalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import info.codementor.flexcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var fuelBaseLabel = "Combustível 1"
    private var fuelCompareLabel = "Combustível 2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel =
            ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)

        binding.evaluateBestFuelButton.setOnClickListener {
            viewModel.evaluateConsumption(
                binding.baseFuelInputField.text.toString(),
                binding.compareFuelInputField.text.toString(),
                binding.baseFuelPriceInputField.text.toString(),
                binding.compareFuelPriceInputField.text.toString(),
            )
            hideKeyboardAndClearFocus(this)
            binding.resultTextView.text = evaluateBestFuel(viewModel.fuel)
        }


        binding.searchBaseFuelButton.setOnClickListener {
            val intent = Intent(this, FuelOptionsActivity::class.java)
            getResultBase.launch(intent)
        }
        binding.searchCompareFuelButton.setOnClickListener {
            val intent = Intent(this, FuelOptionsActivity::class.java)
            getResultCompare.launch(intent)
        }

    }
    private fun hideKeyboardAndClearFocus(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let { view ->
            view.clearFocus()
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun evaluateBestFuel(fuel: Fuel): CharSequence? {

      val result= if(fuel == Fuel.BASE) {
            "O melhor combustível é o $fuelBaseLabel"
        } else {
            "O melhor combustível é o $fuelCompareLabel"
        }
        return result
    }


    private val getResultBase =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val value = it.data?.getStringExtra("consumption")
                val unit = it.data?.getStringExtra("unit")?:"Litro"
                fuelBaseLabel = it.data?.getStringExtra("fuel") ?: "Combustível 1"
                binding.baseFuelInputField.setText(value.toString())
                binding.baseFuelInputContainer.hint = fuelBaseLabel
                binding.baseFuelPriceInputContainer.hint = "Valor do $unit do $fuelBaseLabel"
            }
        }

    private val getResultCompare =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val value = it.data?.getStringExtra("consumption")
                val unit = it.data?.getStringExtra("unit")?:"Litro"
                fuelCompareLabel = it.data?.getStringExtra("fuel") ?: "Combustível 2"
                binding.compareFuelInputField.setText(value.toString())
                binding.compareFuelInputContainer.hint = fuelCompareLabel
                binding.compareFuelPriceInputContainer.hint = "Valor do $unit do $fuelCompareLabel"
            }
        }


}