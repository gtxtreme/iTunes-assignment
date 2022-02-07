package com.gtxtreme.template.navigation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.R
import com.gtxtreme.template.presentation.screen.Screen

class BaseNavigatorImpl(private val fragment: Fragment) : BaseNavigator {

    override fun navigateTo(screen: Screen) {
        screen.navigate()
    }

    override fun back() {
        fragment.findNavController().apply {
            if (!popBackStack() && !navigateUp()) {
                fragment.activity?.onBackPressed()
            }
        }
    }

    private fun Screen.navigate() {
        val navOptions = navOptions {
            anim {
                enter = R.anim.nav_default_enter_anim
                exit = R.anim.nav_default_exit_anim
            }
        }
        fragment.findNavController().navigate(id, bundleOf("key_args" to this), navOptions)
    }
}
