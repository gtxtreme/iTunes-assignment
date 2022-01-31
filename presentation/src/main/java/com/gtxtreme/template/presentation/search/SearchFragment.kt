package com.gtxtreme.template.presentation.search

import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.gtxtreme.template.navigation.BaseNavigator
import com.gtxtreme.template.presentation.base.effect.Effect
import com.gtxtreme.template.presentation.base.fragment.BaseFragment
import com.gtxtreme.template.presentation.base.fragment.BindingProvider
import com.gtxtreme.template.resources.databinding.FragmentSearchAltBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SearchFragment :
    BaseFragment<FragmentSearchAltBinding, SearchScreen, SearchScreenState, BaseNavigator, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModel()
    override val navigator: BaseNavigator by navigator()
    override val bindingProvider: BindingProvider<FragmentSearchAltBinding>
        get() = FragmentSearchAltBinding::inflate

    override fun onState(screenState: SearchScreenState) {

        Timber.d("The current search screen state is$screenState")
    }

    override fun onViewCreated(binding: FragmentSearchAltBinding) {
        super.onViewCreated(binding)
        addTextChangedListener(binding.searchEditText)
    }

    private fun addTextChangedListener(textInputEditText: TextInputEditText) {
        textInputEditText.addTextChangedListener {
            viewModel.onIntent(SearchScreenIntent.SearchContent(it?.toString() ?: ""))
        }
    }

    override fun onEffect(effect: Effect) {
        // TODO Provide onEffect if necessary
        Timber.d("This facilitates the effect")
    }
}
