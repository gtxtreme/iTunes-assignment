package com.gtxtreme.template.navigation

import com.gtxtreme.template.presentation.screen.Screen

interface BaseNavigator : Navigator {

    fun navigateTo(screen: Screen)

    fun back()
}
