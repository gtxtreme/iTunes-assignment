package com.gtxtreme.template.presentation.home

import com.gtxtreme.template.navigation.BaseNavigator
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.presentation.base.effect.Effect
import com.gtxtreme.template.presentation.base.extensions.hide
import com.gtxtreme.template.presentation.base.extensions.show
import com.gtxtreme.template.presentation.base.fragment.BindingProvider
import com.gtxtreme.template.presentation.base.fragment.MainFragment
import com.gtxtreme.template.presentation.base.list.ListComponent
import com.gtxtreme.template.presentation.base.toolbar.ToolbarComponent
import com.gtxtreme.template.presentation.home.list.UIContentFavouriteListRenderer
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

    private val listComponent by component {
        ListComponent(viewModel, recyclerViewId = R.id.homeRecyclerView) {
            addRenderer(UIContentFavouriteListRenderer())
        }
    }

    override fun onState(screenState: HomeScreenState) {
        super.onState(screenState)
        binding?.apply {
            if (screenState.items.size() == 0) {
                emptyText.show()
                homeRecyclerView.hide()
            } else {
                emptyText.hide()
                homeRecyclerView.show()
                listComponent.setData(screenState.items)
            }
        }

    }

    override fun onEffect(effect: Effect) {}
}
