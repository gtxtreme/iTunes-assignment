package com.gtxtreme.template.presentation.search

import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.gtxtreme.template.navigation.BaseNavigator
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.presentation.base.effect.Effect
import com.gtxtreme.template.presentation.base.effect.HideKeyboardEffect
import com.gtxtreme.template.presentation.base.effect.ShowSnackbarEffect
import com.gtxtreme.template.presentation.base.extensions.hide
import com.gtxtreme.template.presentation.base.extensions.hideKeyboard
import com.gtxtreme.template.presentation.base.extensions.show
import com.gtxtreme.template.presentation.base.fragment.BaseFragment
import com.gtxtreme.template.presentation.base.fragment.BindingProvider
import com.gtxtreme.template.presentation.base.list.ListComponent
import com.gtxtreme.template.presentation.base.snackbar.SnackbarComponent
import com.gtxtreme.template.presentation.search.list.UIContentListRenderer
import com.gtxtreme.template.resources.databinding.FragmentSearchAltBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SearchFragment :
    BaseFragment<FragmentSearchAltBinding, SearchScreen, SearchScreenState, BaseNavigator, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModel()
    override val navigator: BaseNavigator by navigator()
    override val bindingProvider: BindingProvider<FragmentSearchAltBinding>
        get() = FragmentSearchAltBinding::inflate

    private val listComponent by component {
        ListComponent(viewModel, R.id.altSearchRecyclerView) {
            addRenderer(UIContentListRenderer())
        }
    }

    private val snackbarComponent by component {
        SnackbarComponent(this)
    }

    override fun onState(screenState: SearchScreenState) {
        if (screenState.list.size() == 0) {
            binding?.emptyTextSearch?.show()
            binding?.altSearchRecyclerView?.hide()
        } else {
            binding?.emptyTextSearch?.hide()
            binding?.altSearchRecyclerView?.show()
            listComponent.setData(screenState.list)
        }
    }

    override fun onViewCreated(binding: FragmentSearchAltBinding) {
        super.onViewCreated(binding)
        addTextChangedListener(binding.searchEditText)
        addEditorActionListener(binding.searchEditText)
    }

    private fun addTextChangedListener(textInputEditText: TextInputEditText) {
        textInputEditText.addTextChangedListener {
            viewModel.onIntent(SearchScreenIntent.SearchContent(it?.toString() ?: ""))
        }
    }

    private fun addEditorActionListener(textInputEditText: TextInputEditText) {
        textInputEditText.setOnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onEffect(HideKeyboardEffect)
                Timber.d("User completed search")
            }
            false
        }
    }

    override fun onEffect(effect: Effect) {
        Timber.d("This facilitates the effect")
        when (effect) {
            is ShowSnackbarEffect -> snackbarComponent.setData(effect)
            is HideKeyboardEffect -> hideKeyboard()
        }
    }
}
