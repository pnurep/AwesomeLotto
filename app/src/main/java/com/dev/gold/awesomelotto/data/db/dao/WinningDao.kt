package com.dev.gold.awesomelotto.data.db.dao

import androidx.room.*
import com.dev.gold.awesomelotto.data.dto.Winning
import com.dev.gold.awesomelotto.data.dto.WinningAndLotto
import io.reactivex.Maybe
import io.reactivex.Single


@Dao
interface WinningDao {

    @Transaction
    @Query("""SELECT * FROM Lotto WHERE lottoId = :lottoId""")
    fun getWinningAndLottoById(lottoId: Int): Single<WinningAndLotto>

    @Query("""SELECT * FROM Winning WHERE winning_id = :id LIMIT 1""")
    fun getWinningById(id: Int): Winning?

    @Query(
        """
        SELECT * FROM Winning
        ORDER BY winning_id ASC
        """
    )
    fun loadWinnings(): Maybe<List<Winning>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(winning1: Winning): Long

    @Query("""DELETE FROM Winning WHERE winning_id = :winningId""")
    fun deleteById(winningId: Int): Single<Int>

    @Query("""DELETE FROM Winning""")
    fun deleteAll(): Single<Int>
}
