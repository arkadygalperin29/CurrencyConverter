package space.arkady.currencyconverter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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

    override fun onResumeFragments() {
        super.onResumeFragments()
        switchNightMode()
    }

    private fun initView() {
        binding.popularButton.setOnClickListener {
            openPopularFragment()
        }
        binding.favoritesButton.setOnClickListener {
            openFavoritesFragment()
        }
    }
    private fun switchNightMode() {
        binding.switchMaterial.setOnCheckedChangeListener {_, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun openPopularFragment() {
        openFragment(R.id.container, PopularFragment.newInstance(), PopularFragment.TAG)
    }

    private fun openFavoritesFragment() {
        openFragment(R.id.container, FavoriteFragment.newInstance(), FavoriteFragment.TAG)
    }
}