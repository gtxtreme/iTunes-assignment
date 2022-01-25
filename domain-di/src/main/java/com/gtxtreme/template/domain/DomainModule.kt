package com.gtxtreme.template.domain

import com.gtxtreme.template.domain.content.GetContentUseCase
import com.gtxtreme.template.domain.content.GetContentUseCaseImpl
import org.koin.dsl.module

var domainModule = module {

    single<GetContentUseCase> {
        GetContentUseCaseImpl(get())
    }
}
