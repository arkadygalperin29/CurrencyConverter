package space.arkady.currencyconverter.data.mappers

import space.arkady.currencyconverter.data.dto.FavoriteEntity
import space.arkady.currencyconverter.domain.model.Currency
import space.arkady.currencyconverter.domain.model.FavoriteCurrency

fun FavoriteEntity.toFavoriteCurrency(): FavoriteCurrency {
return FavoriteCurrency(
    name_currency, value_currency, status_favorite
)
}

fun FavoriteCurrency.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        nameCurrency, valueCurrency, statusFavorite
    )
}