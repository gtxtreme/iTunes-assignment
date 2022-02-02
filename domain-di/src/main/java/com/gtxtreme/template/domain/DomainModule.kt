package com.gtxtreme.template.domain

import com.gtxtreme.template.domain.content.GetContentUseCase
import com.gtxtreme.template.domain.content.GetContentUseCaseImpl
import com.gtxtreme.template.domain.content.GetFavouriteContentUseCase
import com.gtxtreme.template.domain.content.GetFavouriteContentUseCaseImpl
import com.gtxtreme.template.domain.content.MarkAsFavouriteUseCase
import com.gtxtreme.template.domain.content.MarkAsFavouriteUseCaseImpl
import com.gtxtreme.template.domain.content.RemoveFavouriteUseCase
import com.gtxtreme.template.domain.content.RemoveFavouriteUseCaseImpl
import org.koin.dsl.module

var domainModule = module {

    single<GetContentUseCase> {
        GetContentUseCaseImpl(get())
    }

    single<MarkAsFavouriteUseCase> {
        MarkAsFavouriteUseCaseImpl(get())
    }

    single<GetFavouriteContentUseCase> {
        GetFavouriteContentUseCaseImpl(get())
    }

    single<RemoveFavouriteUseCase> {
        RemoveFavouriteUseCaseImpl(get())
    }
}
