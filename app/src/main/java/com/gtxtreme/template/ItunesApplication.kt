package com.gtxtreme.template

import android.app.Application
import com.gtxtreme.template.domain.domainModule
import com.gtxtreme.template.interactor.interactorModule
import com.gtxtreme.template.navigation.navigationModule
import com.gtxtreme.template.presentation.presentationModule
import com.gtxtreme.template.repo.repoModule
import com.gtxtreme.template.service.serviceModule
import org.koin.core.context.startKoin
import timber.log.Timber

class ItunesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            modules(
                serviceModule,
                repoModule,
                domainModule,
                interactorModule,
                navigationModule,
                presentationModule
            )
        }
    }
}