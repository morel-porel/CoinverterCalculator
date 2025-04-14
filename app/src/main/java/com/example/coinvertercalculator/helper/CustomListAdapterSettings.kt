package com.example.coinvertercalculator.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.coinvertercalculator.R
import com.example.coinvertercalculator.data.Settings

class CustomListAdapterSettings (
    val contex: Context,
    val listofSettings: List<Settings>,
    val onClick: (Settings, Int) -> Unit
): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?:LayoutInflater.from(contex).inflate(R.layout.item_settings, parent, false)

        val setting =listofSettings[position]

//        val imageViewPhoto = view.findViewById<ImageView>(R.id.imageview_img)
        val textViewLabel = view.findViewById<TextView>(R.id.textview_label)

//        imageViewPhoto.setImageResource(setting.img)
        textViewLabel.setText(setting.label)

        view.setOnClickListener{
            onClick(setting, position)
        }
        return view

    }

    override fun getCount(): Int = listofSettings.size

    override fun getItem(position: Int): Any = listofSettings[position]

    override fun getItemId(position: Int): Long = position.toLong()


}