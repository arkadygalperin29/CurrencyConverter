package space.arkady.currencyconverter.domain.interactor

import kotlinx.coroutines.flow.Flow
import space.arkady.currencyconverter.domain.model.FavoriteCurrency
import space.arkady.currencyconverter.domain.repository.FavoriteCurrencyRepository
import javax.inject.Inject

class FavoriteCurrencyInteractorImpl @Inject constructor(private val repository: FavoriteCurrencyRepository) :
    FavoriteCurrencyInteractor {
    override fun getAllCurrencyData(): Flow<List<FavoriteCurrency>> {
        return repository.getAllFavoriteCurrency()
    }

    override suspend fun saveData(favoriteCurrency: FavoriteCurrency) {
        return repository.saveFavoriteCurrency(favoriteCurrency)
    }

    override suspend fun deleteData(favoriteCurrency: FavoriteCurrency) {
        return repository.deleteFavoriteCurrency(favoriteCurrency)
    }

    override suspend fun updateFavoriteData(favoriteCurrency: FavoriteCurrency) {
        return repository.updateFavoriteCurrency(favoriteCurrency)
    }
}