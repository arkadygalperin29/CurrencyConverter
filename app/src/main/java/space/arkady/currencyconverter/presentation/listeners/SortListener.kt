package space.arkady.currencyconverter.presentation.listeners

import space.arkady.currencyconverter.presentation.sortmenu.SortingTypes

interface SortListener {
    fun clickAction(sortingTypes: SortingTypes)
}