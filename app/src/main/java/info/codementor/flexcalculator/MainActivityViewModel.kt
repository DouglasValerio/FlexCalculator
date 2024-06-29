package info.codementor.flexcalculator

import androidx.lifecycle.ViewModel
import info.codementor.flexcalculator.models.DistanceBetweenCitiesModel
import java.text.DecimalFormat
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {
    private var baseFuelConsumption: Double = 0.0
    private var compareFuelConsumption: Double = 0.0
    private var baseFuelPrice: Double = 0.0
    private var compareFuelPrice: Double = 0.0
    private var baseFuelLabel: String = "Combustível 1"
    private var compareFuelLabel: String = "Combustível 2"
    var fuel: Fuel = Fuel.BASE

    var travelTrivia = ""

    private val distances = listOf(
        DistanceBetweenCitiesModel("São Paulo (SP)", "Rio de Janeiro (RJ)", 430.0),
        DistanceBetweenCitiesModel("Brasília (DF)", "Goiânia (GO)", 210.0),
        DistanceBetweenCitiesModel("Belo Horizonte (MG)", "Vitória (ES)", 530.0),
        DistanceBetweenCitiesModel("Curitiba (PR)", "Florianópolis (SC)", 300.0),
        DistanceBetweenCitiesModel("Fortaleza (CE)", "Recife (PE)", 800.0),
        DistanceBetweenCitiesModel("Salvador (BA)", "Aracaju (SE)", 330.0),
        DistanceBetweenCitiesModel("Porto Alegre (RS)", "São Paulo (SP)", 1100.0),
        DistanceBetweenCitiesModel("Manaus (AM)", "Belém (PA)", 1300.0)
    )

    private fun getRandomCity(): DistanceBetweenCitiesModel {
        return distances[Random.nextInt(distances.size)]
    }

    fun getTrivia() {
        val city = getRandomCity()
        val fuelPrice = if (fuel == Fuel.BASE) baseFuelPrice else compareFuelPrice
        val higherPrice = if (fuel == Fuel.BASE) compareFuelPrice else baseFuelPrice
        val consumption = if (fuel == Fuel.BASE) baseFuelConsumption else compareFuelConsumption
        val bestFuel = if (fuel == Fuel.BASE) baseFuelLabel else compareFuelLabel
        val worstFuel = if (fuel == Fuel.BASE) compareFuelLabel else baseFuelLabel
        val decimalFormat = DecimalFormat("#.##")
        travelTrivia = "A distância entre ${city.origin} e ${city.destination} é de ${decimalFormat.format(city.distance)} km.\n"+
                "Usando o $bestFuel você gastará R$${decimalFormat.format(fuelPrice * (city.distance/consumption))} em combustível\n\n"+
                "Caso opte por usar o $worstFuel você gastará R$${decimalFormat.format(higherPrice * (city.distance/consumption))} em combustível"
    }

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

    fun setCompareFuelLabel( compareFuelLabelInput: String) {
        compareFuelLabel = compareFuelLabelInput
    }
    fun setBaseFuelLabel(baseFuelLabelInput: String) {
        baseFuelLabel = baseFuelLabelInput
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