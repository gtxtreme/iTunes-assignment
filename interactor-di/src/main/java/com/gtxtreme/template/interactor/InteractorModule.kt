package com.gtxtreme.template.interactor

import com.gtxtreme.template.interactor.content.GetContentInteractor
import com.gtxtreme.template.interactor.content.GetContentInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<GetContentInteractor> {
        GetContentInteractorImpl(get())
    }
}
