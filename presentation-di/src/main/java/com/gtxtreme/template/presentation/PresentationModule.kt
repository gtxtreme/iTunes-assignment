package com.gtxtreme.template.presentation

import com.gtxtreme.template.presentation.home.HomeViewModel
import com.gtxtreme.template.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get(), get()) }
}
