package space.arkady.currencyconverter.data.remote

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import space.arkady.currencyconverter.common.Constants.Companion.BASE_API_KEY
import space.arkady.currencyconverter.data.model.CurrencyResponce

interface ExchangeApiService {
    @GET("{endpoint}")
    fun getExchangeApi(
        @Path("endpoint")
        endpoint: String = "latest",
        @Query("apikey")
        apikey: String = BASE_API_KEY,
        @Query("base")
        base: String,
    ): Flow<CurrencyResponce>
}