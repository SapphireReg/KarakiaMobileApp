package com.example.karakiamobileapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow



@Dao
interface KarakiaDao {

    @Query("SELECT * FROM karakia_table WHERE title LIKE '%' || :searchQuery || '%' ")
    fun getKarakiaByTitle(searchQuery: String): Flow<List<Karakia>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(karakia: Karakia) //suspend allows use of background thread, passed and used later on

    @Update
    fun update(karakia: Karakia)

    @Delete
    fun delete(karakia: Karakia)

}