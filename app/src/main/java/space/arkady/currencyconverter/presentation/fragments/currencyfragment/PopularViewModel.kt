package space.arkady.currencyconverter.presentation.fragments.currencyfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import space.arkady.currencyconverter.common.Constants
import space.arkady.currencyconverter.domain.interactor.FavoriteCurrencyInteractor
import space.arkady.currencyconverter.domain.interactor.CurrencyInteractor
import space.arkady.currencyconverter.domain.model.Currency
import space.arkady.currencyconverter.domain.model.CurrencyItem
import space.arkady.currencyconverter.domain.model.FavoriteCurrency
import space.arkady.currencyconverter.presentation.sortmenu.SortingTypes
import space.arkady.currencyconverter.presentation.utils.sortedByAlphabet
import space.arkady.currencyconverter.presentation.utils.sortedByAlphabetReversed
import space.arkady.currencyconverter.presentation.utils.sortedByHighestValue
import space.arkady.currencyconverter.presentation.utils.sortedByLeastValue
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularInteractor: CurrencyInteractor,
    private val favoriteCurrencyInteractor: FavoriteCurrencyInteractor
) : ViewModel() {

    private var searchCurrency: String = Constants.DEFAULT_CURRENCY

    init {
        sortAlphabet()
    }
    private var _countState: MutableStateFlow<Currency> =
        MutableStateFlow(Currency(null, emptyList()))
    val countState: StateFlow<Currency> = _countState.asStateFlow()

    fun sortCurrency(sortingTypes: SortingTypes) {
        when (sortingTypes) {
            SortingTypes.NAME_UP -> sortAlphabet()
                SortingTypes.NAME_DOWN
            -> sortByAlphabetReversed()
                SortingTypes.VALUE_UP
            -> sortByLeastPrice()
                SortingTypes.VALUE_DOWN
            -> sortByHighestPrice()
        }
    }

    fun addFavorite(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteCurrencyInteractor.saveData(favoriteCurrency)
        }
    }

    fun deleteFavorite(favoriteCurrency: FavoriteCurrency) {
        viewModelScope.launch {
            favoriteCurrencyInteractor.deleteData(favoriteCurrency)
        }
    }

    fun setSearchValue(searchValue: String) {
        searchCurrency = searchValue
        getCurrency(searchValue).map { currency ->
            _countState.value = currency
        }.launchIn(viewModelScope)
    }

    private fun getCurrency(q: String): Flow<Currency> {
        return favoriteCurrencyInteractor.getAllCurrencyData()
            .combine(popularInteractor.getDataCurrency(q)) { favorite, popular ->
                val listFavorite: MutableList<String> = mutableListOf()
                favorite.map { favoriteCurrency ->
                    listFavorite.add(favoriteCurrency.nameCurrency)
                }
                Currency(popular.base, popular.rates.map { currencyItem ->
                    if (listFavorite.contains(currencyItem.nameCurrency)) {
                        CurrencyItem(currencyItem.nameCurrency, currencyItem.valueCurrency, true)
                    } else {
                        currencyItem
                    }
                })
            }

    }

    private fun sortAlphabet(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _countState.value = currency.sortedByAlphabet()
        }.launchIn(viewModelScope)
    }

    private fun sortByAlphabetReversed(q:String = searchCurrency) {
        getCurrency(q).map { currency ->
            _countState.value = currency.sortedByAlphabetReversed()
        }.launchIn(viewModelScope)
    }

    private fun sortByLeastPrice(q: String = searchCurrency) {
        getCurrency(q).map { currency ->
            _countState.value = currency.sortedByLeastValue()
        }
    }
    private fun sortByHighestPrice(q:String = searchCurrency) {
        getCurrency(q).map { currency ->
            _countState.value = currency.sortedByHighestValue()
        }
    }
}