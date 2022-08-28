package space.arkady.currencyconverter.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.arkady.currencyconverter.common.Constants
import space.arkady.currencyconverter.data.remote.ExchangeApiService
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

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
}