package com.dev.gold.awesomelotto.di

import com.dev.gold.awesomelotto.AlApplication
import com.dev.gold.awesomelotto.di.modules.ApplicationModule
import com.dev.gold.awesomelotto.di.modules.RepositoryModule
import com.dev.gold.awesomelotto.di.modules.UtilsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        UtilsModule::class,
        RepositoryModule::class,
        ActivityContributor::class
    ]
)
interface ApplicationComponent : AndroidInjector<AlApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance instance: AlApplication): ApplicationComponent
    }
}
