package space.arkady.currencyconverter.presentation.listeners

import space.arkady.currencyconverter.domain.model.FavoriteCurrency

fun interface DeleteFavoriteClickListener {
    fun clickAction(favoriteCurrency: FavoriteCurrency)
}