package com.loperilla.rawg.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.loperilla.rawg.datasource.database.entity.QueryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QueryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNewQuery(query: QueryEntity)

    @Query("SELECT * FROM QueryEntity")
    fun getPreviousQuery(): Flow<List<QueryEntity>>
}