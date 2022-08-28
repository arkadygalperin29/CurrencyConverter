package space.arkady.currencyconverter.data.repository

import kotlinx.coroutines.flow.Flow
import space.arkady.currencyconverter.data.model.CurrencyResponce
import space.arkady.currencyconverter.data.remote.ExchangeApiService
import space.arkady.currencyconverter.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val exchangeApiService: ExchangeApiService) :
    CurrencyRepository {
    override fun getRepository(baseCurrency: String): Flow<CurrencyResponce> {
        return exchangeApiService.getExchangeApi(base = baseCurrency)
    }
}