package space.arkady.currencyconverter.presentation.listeners

import space.arkady.currencyconverter.domain.model.CurrencyItem

fun interface AddFavoriteClickListener {
    fun actionClick(currencyItem: CurrencyItem)
}