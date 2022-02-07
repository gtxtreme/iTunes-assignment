package com.gtxtreme.template.navigation

import androidx.fragment.app.Fragment
import org.koin.dsl.module

val navigationModule = module {
    factory<BaseNavigator> { (fragment: Fragment) -> BaseNavigatorImpl(fragment) }
}
