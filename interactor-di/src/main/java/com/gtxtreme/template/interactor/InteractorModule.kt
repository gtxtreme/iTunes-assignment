package com.gtxtreme.template.interactor

import com.gtxtreme.template.interactor.base.CoroutineContextController
import com.gtxtreme.template.interactor.base.CoroutineContextControllerImpl
import com.gtxtreme.template.interactor.content.GetContentInteractor
import com.gtxtreme.template.interactor.content.GetContentInteractorImpl
import com.gtxtreme.template.interactor.content.UIContentListResultsMapper
import org.koin.dsl.module

val interactorModule = module {

    single {
        UIContentListResultsMapper()
    }

    single<GetContentInteractor> {
        GetContentInteractorImpl(get(), get(), get())
    }

    single<CoroutineContextController> {
        CoroutineContextControllerImpl()
    }
}
