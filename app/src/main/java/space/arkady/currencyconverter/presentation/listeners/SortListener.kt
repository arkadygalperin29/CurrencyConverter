package space.arkady.currencyconverter.presentation.listeners

import space.arkady.currencyconverter.presentation.sortmenu.SortingTypes

fun interface SortListener {
    fun clickAction(sortingTypes: SortingTypes)
}