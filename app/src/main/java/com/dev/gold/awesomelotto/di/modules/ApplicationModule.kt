package com.dev.gold.awesomelotto.di.modules

import android.content.Context
import androidx.room.Room
import com.dev.gold.awesomelotto.AlApplication
import com.dev.gold.awesomelotto.data.APPLICATION_CONTEXT
import com.dev.gold.awesomelotto.data.db.AlDb
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Named
import javax.inject.Singleton


@Module
object ApplicationModule {

    @Reusable
    @Provides
    @Named(APPLICATION_CONTEXT)
    fun provideContext(context: AlApplication): Context = context

    // db region

    @Singleton
    @Provides
    fun provideDb(
        @Named(APPLICATION_CONTEXT)
        context: Context
    ): AlDb = Room
        .databaseBuilder(context, AlDb::class.java, "awesome_lotto.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideLottoDao(db: AlDb) = db.lottoDao()

    @Singleton
    @Provides
    fun provideWinningDao(db: AlDb) = db.winningDao()

    // db endRegion
}
