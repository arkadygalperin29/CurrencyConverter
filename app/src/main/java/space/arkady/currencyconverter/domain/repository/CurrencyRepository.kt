package space.arkady.currencyconverter.domain.repository

import kotlinx.coroutines.flow.Flow
import space.arkady.currencyconverter.data.model.CurrencyResponce

interface CurrencyRepository {
    fun getRepository(baseCurrency: String): Flow<CurrencyResponce>
}