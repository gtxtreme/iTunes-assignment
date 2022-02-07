package com.gtxtreme.template

import android.app.Application
import com.gtxtreme.template.domain.domainModule
import com.gtxtreme.template.interactor.interactorModule
import com.gtxtreme.template.navigation.navigationModule
import com.gtxtreme.template.presentation.presentationModule
import com.gtxtreme.template.repo.repoModule
import com.gtxtreme.template.service.serviceModule
import com.gtxtreme.template.BuildConfig
import com.wednesday.template.domain.domainModule
import com.wednesday.template.interactor.interactorModule
import com.wednesday.template.navigation.navigationModule
import com.wednesday.template.presentation.presentationModule
import com.wednesday.template.repo.repoModule
import com.wednesday.template.service.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ItunesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(applicationContext)
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