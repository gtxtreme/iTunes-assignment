package com.wednesday.template.presentation.weather.search

import androidx.core.widget.addTextChangedListener
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.resources.databinding.FragmentSearchBinding
import com.wednesday.template.navigation.search.SearchNavigator
import com.wednesday.template.presentation.base.effect.Effect
import com.wednesday.template.presentation.base.fragment.BindingProvider
import com.wednesday.template.presentation.base.fragment.MainFragment
import com.wednesday.template.presentation.base.list.ListComponent
import com.wednesday.template.presentation.base.toolbar.ToolbarComponent
import com.wednesday.template.presentation.weather.search.list.UICityListRenderer
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment :
    MainFragment<FragmentSearchBinding, SearchScreen, SearchScreenState, SearchNavigator, SearchViewModel>() {

    override val toolbarComponent: ToolbarComponent = ToolbarComponent(this, onBackClicked = {
        viewModel.onIntent(SearchScreenIntent.Back)
    })

    override val viewModel: SearchViewModel by viewModel()

    override val navigator: SearchNavigator by navigator()

    override val bindingProvider: BindingProvider<FragmentSearchBinding> =
        FragmentSearchBinding::inflate

    private val listComponent by component {
        ListComponent(listViewModel = viewModel, recyclerViewId = R.id.searchRecyclerView) {
            addRenderer(UICityListRenderer())
        }
    }

    override fun onViewCreated(binding: FragmentSearchBinding) {
        super.onViewCreated(binding)
        addTextListener(binding)
    }

    override fun onState(screenState: SearchScreenState) {
        super.onState(screenState)
        listComponent.setData(screenState.searchList)
    }

    private fun addTextListener(binding: FragmentSearchBinding) = with(binding) {
        searchEditText.addTextChangedListener {
            it?.let { viewModel.onIntent(SearchScreenIntent.SearchCities(it.toString())) }
        }
    }

    override fun onEffect(effect: Effect) {
    }
}
