package space.arkady.currencyconverter.presentation.fragments.currencyfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import space.arkady.currencyconverter.R
import space.arkady.currencyconverter.common.Constants
import space.arkady.currencyconverter.databinding.FragmentCurrencyBinding
import space.arkady.currencyconverter.presentation.fragments.currencyfragment.popular_adapter.PopularAdapter
import space.arkady.currencyconverter.presentation.listeners.AddFavoriteClickListener
import space.arkady.currencyconverter.presentation.listeners.DeleteFavoriteClickListener
import space.arkady.currencyconverter.presentation.listeners.SortListener
import space.arkady.currencyconverter.presentation.listeners.SpinnerListener
import space.arkady.currencyconverter.presentation.sortmenu.SortMenu
import space.arkady.currencyconverter.presentation.spinner.Spinner
import space.arkady.currencyconverter.presentation.utils.toFavoriteCurrency


@AndroidEntryPoint
class PopularFragment : Fragment(R.layout.fragment_currency) {

    private lateinit var binding: FragmentCurrencyBinding

    private val popularViewModel: PopularViewModel by viewModels()

    private val adapter by lazy { PopularAdapter(addFavoriteCurrency, deleteFavoriteCurrency) }

    companion object {
        const val TAG = "PopularFragment"
        fun newInstance() = PopularFragment()
    }

    private val addFavoriteCurrency =
        AddFavoriteClickListener { currencyItem -> popularViewModel.addFavorite(currencyItem.toFavoriteCurrency()) }

    private val deleteFavoriteCurrency =
        DeleteFavoriteClickListener { favoriteCurrency ->
            popularViewModel.deleteFavorite(
                favoriteCurrency
            )
        }

    private val sortListener =
        SortListener { sortingTypes -> popularViewModel.sortCurrency(sortingTypes) }

    private val spinnerListener =
        SpinnerListener { query -> popularViewModel.setSearchValue(query) }

/*    private fun switchNightMode() {
        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }*/



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


/*   private fun switchNightMode() {
        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }*/


    private fun initView() {
        binding.sortImageButton.setOnClickListener { view: View ->
            SortMenu.sortMenu(view, R.menu.sorting_menu, sortListener, requireContext())
        }

        Spinner.createSpinner(
            binding.spinnerCurrency,
            requireContext(),
            Constants.BASE_CURRENCY_LIST,
            spinnerListener
        )

        binding.recyclerCurrency.adapter = adapter

        popularViewModel.countState.onEach { currency ->
            adapter.submitItem(currency.rates)
        }.launchIn(lifecycleScope)
    }
}