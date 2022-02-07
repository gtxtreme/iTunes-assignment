package com.gtxtreme.template.interactor

import com.gtxtreme.template.interactor.base.CoroutineContextController
import com.gtxtreme.template.interactor.base.CoroutineContextControllerImpl
import com.gtxtreme.template.interactor.content.FavouriteContentInteractor
import com.gtxtreme.template.interactor.content.FavouriteContentInteractorImpl
import com.gtxtreme.template.interactor.content.GetContentInteractor
import com.gtxtreme.template.interactor.content.GetContentInteractorImpl
import com.gtxtreme.template.interactor.content.UIContentListResultsMapper
import com.gtxtreme.template.interactor.content.UIContentListResultsMapperImpl
import org.koin.dsl.module

val interactorModule = module {

    single<UIContentMapper> {
        UIContentMapperImpl()
    }

    single<FavouriteContentInteractor> {
        FavouriteContentInteractorImpl(get(), get(), get(), get(), get())
    }

    single<UIContentListResultsMapper> {
        UIContentListResultsMapperImpl(get())
    }

    single<GetContentInteractor> {
        GetContentInteractorImpl(get(), get(), get(), get())
    }

    single<CoroutineContextController> {
        CoroutineContextControllerImpl()
    }
}
