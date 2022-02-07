package com.gtxtreme.template.repo

import com.gtxtreme.template.content.DomainContentMapper
import com.gtxtreme.template.content.DomainContentMapperImpl
import org.koin.dsl.module

val repoModule = module {
    single<ContentRepository> {
        ContentRepoImpl(get(), get(), get())
    }

    single<DomainContentMapper> {
        DomainContentMapperImpl()
    }
}
