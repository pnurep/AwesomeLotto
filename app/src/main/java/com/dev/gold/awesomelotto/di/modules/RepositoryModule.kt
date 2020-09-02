package com.dev.gold.awesomelotto.di.modules

import com.dev.gold.awesomelotto.data.db.dao.LottoDao
import com.dev.gold.awesomelotto.data.db.dao.WinningDao
import com.dev.gold.awesomelotto.repository.LottoRepository
import com.dev.gold.awesomelotto.repository.WinningRepository
import com.dev.gold.awesomelotto.repository.impl.LottoRepositoryImpl
import com.dev.gold.awesomelotto.repository.impl.WinningRepositoryImpl
import com.dev.gold.awesomelotto.repository.utils.ApiManager
import dagger.Module
import dagger.Provides
import dagger.Reusable


@Module
object RepositoryModule {

    @Reusable
    @Provides
    internal fun provideLottoRepository( // TODO : 추후 모듈화를 위해 일단 internal...
        lottoDao: LottoDao
    ): LottoRepository = LottoRepositoryImpl(
        lottoDao
    )

    @Reusable
    @Provides
    internal fun provideWinningRepository(
        winningDao: WinningDao,
        lottoDao: LottoDao,
        apiManager: ApiManager
    ): WinningRepository = WinningRepositoryImpl(
        apiManager,
        winningDao,
        lottoDao
    )
}
