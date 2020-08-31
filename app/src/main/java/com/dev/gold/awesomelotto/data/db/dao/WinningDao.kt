package com.dev.gold.awesomelotto.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.gold.awesomelotto.data.dto.Winning
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface WinningDao {

    @Query(
        """
        SELECT * FROM winning
        WHERE id = :id LIMIT 1
        """
    )
    fun getWinningById(id: Int): Single<Winning>

    @Query(
        """
        SELECT * FROM winning
        ORDER BY id DESC
        """
    )
    fun loadWinnings(): Flowable<List<Winning>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(winning: List<Winning>)

    @Query("""DELETE FROM winning""")
    fun deleteAll(): Single<Int>
}
