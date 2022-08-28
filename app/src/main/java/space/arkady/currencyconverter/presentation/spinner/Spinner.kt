package space.arkady.currencyconverter.presentation.spinner

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import space.arkady.currencyconverter.presentation.listeners.SpinnerListener

abstract class Spinner {
    companion object {
        fun createSpinner(
            spinner: Spinner,
            context: Context,
            list: List<String>,
            spinnerListener: SpinnerListener
        ) {
            spinner.adapter =
                ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, list)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    spinnerListener.clickAction(list[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }
}