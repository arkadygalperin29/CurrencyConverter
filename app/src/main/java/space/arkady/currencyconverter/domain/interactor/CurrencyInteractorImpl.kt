package space.arkady.currencyconverter.domain.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import space.arkady.currencyconverter.domain.model.Currency
import space.arkady.currencyconverter.domain.repository.CurrencyRepository
import space.arkady.currencyconverter.domain.utils.toCurrencyResponce
import javax.inject.Inject

class CurrencyInteractorImpl @Inject constructor(private val repository: CurrencyRepository) :
    CurrencyInteractor {
    override fun getDataCurrency(baseCurrency: String): Flow<Currency> {
        return repository.getRepository(baseCurrency).map { currencyResponce ->
            currencyResponce.toCurrencyResponce()
        }
    }
}