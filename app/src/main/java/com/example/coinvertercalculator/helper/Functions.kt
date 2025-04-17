package com.example.coinvertercalculator.helper

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import com.example.coinvertercalculator.R

fun min(a: Int, b: Int): Int {
    return if (a <= b) a else b
}

fun showBasicDialogue(
    context: Context,
    title: String,
    description: String,
    confirmText: String,
    onConfirm: () -> Unit
) {
    val builder = AlertDialog.Builder(context)
    val inflater = LayoutInflater.from(context)
    val dialogView = inflater.inflate(R.layout.basic_dialogue_layout, null)

    val titleTextView = dialogView.findViewById<TextView>(R.id.dialog_title)
    val descriptionTextView = dialogView.findViewById<TextView>(R.id.dialog_description)
    val cancelButton = dialogView.findViewById<Button>(R.id.button_cancel)
    val confirmButton = dialogView.findViewById<Button>(R.id.button_confirm)

    confirmButton.setText(confirmText)

    builder.setView(dialogView)
    val alertDialog = builder.create()

    titleTextView.text = title
    descriptionTextView.text = description

    confirmButton.setOnClickListener {
        onConfirm()
        alertDialog.dismiss()
    }

    cancelButton.setOnClickListener {
        alertDialog.dismiss()
    }

    alertDialog.show()
}
