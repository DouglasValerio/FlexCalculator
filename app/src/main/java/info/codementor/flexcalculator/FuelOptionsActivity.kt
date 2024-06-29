package info.codementor.flexcalculator

import FuelOptionAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import info.codementor.flexcalculator.databinding.ActivityFuelOptionsBinding
import info.codementor.flexcalculator.models.FuelOptionModel

class FuelOptionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFuelOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFuelOptionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val titles = resources.getStringArray(R.array.fuelOptions)
        val subtitles = resources.getStringArray(R.array.fuelUnits)
        val values = resources.getStringArray(R.array.fuelConsumption)

        val dataModelList = titles.zip(subtitles).zip(values) { (title, subtitle), value ->
            FuelOptionModel(title, subtitle, value)
        }

        val adapter = FuelOptionAdapter(this, dataModelList)
        view.adapter = adapter

        binding.lvFuelOptions.setOnItemClickListener { _, _, position, _ ->
             val selectedItem = dataModelList[position]
            intent.putExtra("unit", selectedItem.unit)
            intent.putExtra("fuel", selectedItem.label)
            intent.putExtra("consumption", selectedItem.consumption)
            setResult(RESULT_OK, intent)
            finish()
        }


    }
}