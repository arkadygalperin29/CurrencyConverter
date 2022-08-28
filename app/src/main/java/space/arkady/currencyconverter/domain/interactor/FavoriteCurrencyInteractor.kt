package space.arkady.currencyconverter.domain.interactor

import kotlinx.coroutines.flow.Flow
import space.arkady.currencyconverter.domain.model.FavoriteCurrency

interface FavoriteCurrencyInteractor {
    fun getAllCurrencyData(): Flow<List<FavoriteCurrency>>

    suspend fun saveData(favoriteCurrency: FavoriteCurrency)

    suspend fun deleteData(favoriteCurrency: FavoriteCurrency)

    suspend fun updateFavoriteData(favoriteCurrency: FavoriteCurrency)
}