// CustomAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import info.codementor.flexcalculator.R
import info.codementor.flexcalculator.models.FuelOptionModel

class FuelOptionAdapter(private val context: Context, private val dataSource: List<FuelOptionModel>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = dataSource.size

    override fun getItem(position: Int): Any = dataSource[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = convertView ?: inflater.inflate(R.layout.fuel_option_item, parent, false)

        val titleTextView = rowView.findViewById<TextView>(R.id.item_title)
        val subtitleTextView = rowView.findViewById<TextView>(R.id.item_subtitle)

        val item = getItem(position) as FuelOptionModel

        titleTextView.text = item.label
        subtitleTextView.text = "${item.consumption} km/${item.unit}"

        return rowView
    }
}
