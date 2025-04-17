    package com.example.coinvertercalculator.helper

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.coinvertercalculator.R
    import com.example.coinvertercalculator.data.Currency

    class CurrencyRecyclerViewAdapter (private val listOfCurrencies: List<Currency>)
        : RecyclerView.Adapter<CurrencyRecyclerViewAdapter.ItemViewHolder>() {
        class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val photo = view.findViewById<ImageView>(R.id.currency_photo)
            val currencyAcronym = view.findViewById<TextView>(R.id.currency_acronym)
            val currencyName = view.findViewById<TextView>(R.id.currency_name)
            val currencyRate = view.findViewById<TextView>(R.id.currency_rate)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_view, parent, false)
            return ItemViewHolder(view)
        }

        override fun onBindViewHolder(
            holder: ItemViewHolder,
            position: Int
        ) {
            val item = listOfCurrencies[position]

            holder.photo.setImageResource(item.currencyPhoto)
            holder.currencyAcronym.setText(item.currencyAcronym)
            holder.currencyName.setText(item.currencyName)
            holder.currencyRate.setText(item.currencyRate)
        }

        override fun getItemCount(): Int = listOfCurrencies.size
    }