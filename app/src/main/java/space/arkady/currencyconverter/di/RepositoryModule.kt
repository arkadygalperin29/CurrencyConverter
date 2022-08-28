package space.arkady.currencyconverter.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.arkady.currencyconverter.data.dto.FavoriteDao
import space.arkady.currencyconverter.data.remote.ExchangeApiService
import space.arkady.currencyconverter.data.repository.CurrencyRepositoryImpl
import space.arkady.currencyconverter.data.repository.FavoriteCurrencyRepositoryImpl
import space.arkady.currencyconverter.domain.repository.CurrencyRepository
import space.arkady.currencyconverter.domain.repository.FavoriteCurrencyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(exchangeApiService: ExchangeApiService): CurrencyRepository {
        return CurrencyRepositoryImpl(exchangeApiService = exchangeApiService)
    }

    @Provides
    @Singleton
    fun provideFavoriteCurrencyRepository(roomFavoriteDao: FavoriteDao): FavoriteCurrencyRepository {
        return FavoriteCurrencyRepositoryImpl(roomFavoriteDao)
    }
}