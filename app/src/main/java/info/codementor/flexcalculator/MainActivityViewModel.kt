package info.codementor.flexcalculator

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var baseFuelConsumption: Double = 0.0
    private var compareFuelConsumption: Double = 0.0
    private var baseFuelPrice: Double = 0.0
    private var compareFuelPrice: Double = 0.0
    var fuel: Fuel = Fuel.BASE


    private fun setValue(
        baseFuelInput: String,
        compareFuelConsumptionInput: String,
        baseFuelPriceInput: String,
        compareFuelPriceInput: String
    ) {
        baseFuelConsumption = baseFuelInput.toDoubleOrNull() ?: 0.0
        compareFuelConsumption = compareFuelConsumptionInput.toDoubleOrNull() ?: 0.0
        baseFuelPrice = baseFuelPriceInput.toDoubleOrNull() ?: 0.0
        compareFuelPrice = compareFuelPriceInput.toDoubleOrNull() ?: 0.0
    }

    fun evaluateConsumption(
        baseFuelInput: String,
        compareFuelConsumptionInput: String,
        baseFuelPriceInput: String,
        compareFuelPriceInput: String
    ) {
        setValue(baseFuelInput, compareFuelConsumptionInput, baseFuelPriceInput, compareFuelPriceInput)
        val baseRatio = baseFuelPrice / baseFuelConsumption
        val compareRatio = compareFuelPrice / compareFuelConsumption

        fuel = if (baseRatio > compareRatio) {
            Fuel.COMPARED
        } else {
            Fuel.BASE
        }
    }

}

enum class Fuel {
    BASE, COMPARED
}