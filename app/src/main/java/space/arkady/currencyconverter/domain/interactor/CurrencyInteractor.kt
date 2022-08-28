package space.arkady.currencyconverter.domain.interactor

import kotlinx.coroutines.flow.Flow
import space.arkady.currencyconverter.domain.model.Currency

interface CurrencyInteractor {
    fun getDataCurrency(baseCurrency: String): Flow<Currency>
}