package space.arkady.currencyconverter.presentation.listeners

import space.arkady.currencyconverter.domain.model.FavoriteCurrency

interface DeleteFavoriteClickListener {
    fun clickAction(favoriteCurrency: FavoriteCurrency)
}