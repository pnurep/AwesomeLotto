package com.dev.gold.awesomelotto.data.db.dao

import androidx.room.*
import com.dev.gold.awesomelotto.data.dto.Lotto
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface LottoDao {

    // NOTE : where the value emitted on onSuccess is the list of row ids of the items inserted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lotto: List<Lotto>): Single<List<Long>>

    @Update
    fun update(lotto: Lotto): Single<Int>

    @Update
    fun updateAll(lotto: List<Lotto>): Single<Int>

    // NOTE : where the value emitted on onSuccess is the number of rows affected by update/delete
    @Query("""DELETE FROM lotto""")
    fun deleteAll(): Single<Int>

    @Query(
        """
        SELECT * FROM lotto
        ORDER BY id DESC"""
    )
    fun loadLotto(): Flowable<List<Lotto>>
}
