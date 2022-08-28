package space.arkady.currencyconverter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import space.arkady.currencyconverter.R
import space.arkady.currencyconverter.common.openFragment
import space.arkady.currencyconverter.databinding.ActivityMainBinding
import space.arkady.currencyconverter.presentation.fragments.currencyfragment.PopularFragment
import space.arkady.currencyconverter.presentation.fragments.favoritefragment.FavoriteFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openPopularFragment()
        initView()
    }

    private fun initView() {
        binding.popularButton.setOnClickListener {
            openPopularFragment()
        }
        binding.favoritesButton.setOnClickListener {
            openFavoritesFragment()
        }
    }

    private fun openPopularFragment() {
        openFragment(R.id.container, PopularFragment.newInstance(), PopularFragment.TAG)
    }

    private fun openFavoritesFragment() {
        openFragment(R.id.container, FavoriteFragment.newInstance(), FavoriteFragment.TAG)
    }
}