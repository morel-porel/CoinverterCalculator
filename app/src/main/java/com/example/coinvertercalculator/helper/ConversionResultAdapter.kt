package com.example.coinvertercalculator.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.coinvertercalculator.R
import com.example.coinvertercalculator.data.ConversionResult

class ConversionResultAdapter (
    context: Context,
    resource: Int,
    objects:List<ConversionResult>
): ArrayAdapter<ConversionResult>(context, resource, objects){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if(itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_conversion, parent, false)
        }
        val conversionResult = getItem(position)!!
        val tv_baseCurrency = itemView!!.findViewById<TextView>(R.id.tv_baseCurrency)
        val tv_targetCurrency = itemView!!.findViewById<TextView>(R.id.tv_targetCurrency)
        val tv_amount = itemView.findViewById<TextView>(R.id.tv_amount)
        val tv_convertedAmount = itemView.findViewById<TextView>(R.id.tv_convertedAmount)

        tv_baseCurrency.text = "${conversionResult.amount} ${conversionResult.baseCurrency}"
        tv_targetCurrency.text = "to ${conversionResult.targetCurrency}"
        tv_amount.text = "Amount: ${conversionResult.amount}"
        tv_convertedAmount.text = "Converted: ${conversionResult.convertedAmount}"
        return itemView
    }
}