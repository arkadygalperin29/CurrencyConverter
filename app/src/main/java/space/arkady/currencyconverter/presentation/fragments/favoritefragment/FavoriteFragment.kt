package space.arkady.currencyconverter.presentation.fragments.favoritefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import space.arkady.currencyconverter.R
import space.arkady.currencyconverter.common.Constants
import space.arkady.currencyconverter.databinding.FragmentFavoriteBinding
import space.arkady.currencyconverter.domain.model.FavoriteCurrency
import space.arkady.currencyconverter.presentation.fragments.favoritefragment.favoriteadapter.FavoriteAdapter
import space.arkady.currencyconverter.presentation.listeners.DeleteFavoriteClickListener
import space.arkady.currencyconverter.presentation.listeners.SortListener
import space.arkady.currencyconverter.presentation.listeners.SpinnerListener
import space.arkady.currencyconverter.presentation.sortmenu.SortMenu
import space.arkady.currencyconverter.presentation.sortmenu.SortingTypes
import space.arkady.currencyconverter.presentation.spinner.Spinner


@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private lateinit var binding: FragmentFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private val adapter by lazy { FavoriteAdapter(deleteFavoriteCurrency) }

    companion object {
        const val TAG = "FavoriteFragment"
        fun newInstance() = FavoriteFragment()
    }

    private val deleteFavoriteCurrency = object : DeleteFavoriteClickListener {
        override fun clickAction(favoriteCurrency: FavoriteCurrency) {
            favoriteViewModel.deleteFavorite(favoriteCurrency)
        }
    }

    private val sortListener = object : SortListener {
        override fun clickAction(sortingTypes: SortingTypes) {
            favoriteViewModel.sortCurrency(sortingTypes)
        }
    }

    private val spinnerListener = object : SpinnerListener {
        override fun clickAction(query: String) {
            favoriteViewModel.setSearchValue(query)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

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

        binding.recyclerFavorite.adapter = adapter

        favoriteViewModel.favoriteState.onEach { currency ->
            adapter.submitItems(currency.rates)
        }.launchIn(lifecycleScope)
    }
}