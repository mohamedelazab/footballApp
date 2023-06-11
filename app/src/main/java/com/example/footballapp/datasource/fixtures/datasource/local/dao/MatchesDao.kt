package com.example.footballapp.datasource.fixtures.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.footballapp.datasource.fixtures.models.Match
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MatchesDao {

    @Query("SELECT * FROM match_table")
    fun readMatches(): Single<List<Match>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(match: Match): Completable

    @Query("DELETE FROM match_table WHERE localId = :localId")
    fun deleteMatch(localId: Int): Completable

}