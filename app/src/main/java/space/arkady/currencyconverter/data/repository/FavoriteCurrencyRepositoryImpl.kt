package space.arkady.currencyconverter.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import space.arkady.currencyconverter.data.dto.FavoriteDao
import space.arkady.currencyconverter.data.mappers.toFavoriteCurrency
import space.arkady.currencyconverter.data.mappers.toFavoriteEntity
import space.arkady.currencyconverter.domain.model.FavoriteCurrency
import space.arkady.currencyconverter.domain.repository.FavoriteCurrencyRepository
import javax.inject.Inject

class FavoriteCurrencyRepositoryImpl @Inject constructor(private val favoriteDao: FavoriteDao): FavoriteCurrencyRepository {
    override fun getAllFavoriteCurrency(): Flow<List<FavoriteCurrency>> {
        return favoriteDao.getAllFavorites().map { list ->
            list.map { favoriteEntity ->
                favoriteEntity.toFavoriteCurrency()
            }
        }
    }

    override suspend fun saveFavoriteCurrency(favoriteCurrency: FavoriteCurrency) {
        return withContext(Dispatchers.IO) {
            favoriteDao.saveFavorite(favoriteCurrency.toFavoriteEntity())
        }
    }

    override suspend fun deleteFavoriteCurrency(favoriteCurrency: FavoriteCurrency) {
        return withContext(Dispatchers.IO) {
            favoriteDao.deleteFavorite(favoriteCurrency.toFavoriteEntity())
        }
    }

    override suspend fun updateFavoriteCurrency(favoriteCurrency: FavoriteCurrency) {
        return withContext(Dispatchers.IO) {
            favoriteDao.updateFavorite(favoriteCurrency.toFavoriteEntity())
        }
    }
}