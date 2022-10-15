package com.example.karakiamobileapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface KarakiaDao {

    fun getKarakia(query: String): Flow<List<Karakia>>

    @Query("SELECT * FROM karakia_table WHERE title LIKE '%' || :searchQuery")
    fun getKarakiaByTitle(searchQuery: String): Flow<List<Karakia>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(karakia: Karakia) //suspend allows use of background thread, passed and used later on
}