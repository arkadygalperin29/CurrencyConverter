package space.arkady.currencyconverter.domain.repository

import kotlinx.coroutines.flow.Flow
import space.arkady.currencyconverter.domain.model.FavoriteCurrency

interface FavoriteCurrencyRepository {

    fun getAllFavoriteCurrency(): Flow<List<FavoriteCurrency>>

    suspend fun saveFavoriteCurrency(favoriteCurrency: FavoriteCurrency)

    suspend fun deleteFavoriteCurrency(favoriteCurrency: FavoriteCurrency)

    suspend fun updateFavoriteCurrency(favoriteCurrency: FavoriteCurrency)
}