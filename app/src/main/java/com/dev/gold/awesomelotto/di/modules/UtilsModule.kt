package com.dev.gold.awesomelotto.di.modules

import com.dev.gold.awesomelotto.repository.utils.ApiManager
import com.dev.gold.awesomelotto.repository.utils.ApiManagerImpl
import com.dev.gold.awesomelotto.repository.utils.RetrofitManager
import com.google.gson.*
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton


@Module
object UtilsModule {

    @Reusable
    @Provides
    fun provideGson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()

    @Singleton
    @Provides
    fun provideAPIProvider(
        gson: Gson,
    ): ApiManager = ApiManagerImpl(
        RetrofitManager.build(gson)
    )
}
