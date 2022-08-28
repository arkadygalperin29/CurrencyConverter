package space.arkady.currencyconverter.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.openFragment(container: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
        .replace(container, fragment, tag)
        .addToBackStack(tag)
        .commit()
}