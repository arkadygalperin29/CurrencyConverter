package space.arkady.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.arkady.currencyconverter.common.Constants
import space.arkady.currencyconverter.data.dto.FavoriteDao
import space.arkady.currencyconverter.data.dto.FavoriteDatabase
import space.arkady.currencyconverter.data.remote.ExchangeApiService
import space.arkady.currencyconverter.data.repository.CurrencyRepositoryImpl
import space.arkady.currencyconverter.data.repository.FavoriteCurrencyRepositoryImpl
import space.arkady.currencyconverter.domain.interactor.CurrencyInteractor
import space.arkady.currencyconverter.domain.interactor.CurrencyInteractorImpl
import space.arkady.currencyconverter.domain.interactor.FavoriteCurrencyInteractor
import space.arkady.currencyconverter.domain.interactor.FavoriteCurrencyInteractorImpl
import space.arkady.currencyconverter.domain.repository.CurrencyRepository
import space.arkady.currencyconverter.domain.repository.FavoriteCurrencyRepository
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGsonBuilder(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRetrofitInstance(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeApiService(retrofit: Retrofit): ExchangeApiService {
        return retrofit.create(ExchangeApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            "currency_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao {
        return favoriteDatabase.getFavoriteDao()
    }

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

    @Provides
    @Singleton
    fun provideCurrencyInteractor(currencyRepository: CurrencyRepository): CurrencyInteractor {
        return CurrencyInteractorImpl(currencyRepository)
    }

    @Provides
    @Singleton
    fun provideFavoriteCurrencyInteractor(
        favoriteCurrencyRepository: FavoriteCurrencyRepository
    ): FavoriteCurrencyInteractor {
        return FavoriteCurrencyInteractorImpl(favoriteCurrencyRepository)
    }
}