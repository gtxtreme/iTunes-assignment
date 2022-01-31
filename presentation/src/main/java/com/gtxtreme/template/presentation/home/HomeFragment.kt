package com.gtxtreme.template.presentation.home

import com.gtxtreme.template.navigation.BaseNavigator
import com.gtxtreme.template.presentation.base.effect.Effect
import com.gtxtreme.template.presentation.base.extensions.setUIText
import com.gtxtreme.template.presentation.base.fragment.BindingProvider
import com.gtxtreme.template.presentation.base.fragment.MainFragment
import com.gtxtreme.template.presentation.base.toolbar.ToolbarComponent
import com.gtxtreme.template.resources.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    MainFragment<FragmentHomeBinding, HomeScreen, HomeScreenState, BaseNavigator, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModel()
    override val navigator: BaseNavigator by navigator()
    override val bindingProvider: BindingProvider<FragmentHomeBinding>
        get() = FragmentHomeBinding::inflate

    override val toolbarComponent: ToolbarComponent = ToolbarComponent(this) {
        viewModel.onIntent(HomeScreenIntent.Search)
    }

    override fun onState(screenState: HomeScreenState) {
        super.onState(screenState)

        binding?.apply {
            descriptionText.setUIText(screenState.text)
        }
    }

    override fun onEffect(effect: Effect) {}
}
