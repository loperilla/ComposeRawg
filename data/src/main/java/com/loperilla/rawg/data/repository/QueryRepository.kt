package com.loperilla.rawg.data.repository

import com.loperilla.rawg.datasource.database.dao.QueryDao
import com.loperilla.rawg.datasource.database.entity.QueryEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QueryRepository @Inject constructor(
    private val queryDao: QueryDao
) {
    fun insertNewQuery(query: String) =
        queryDao.insertNewQuery(QueryEntity(query = query))

    fun collectAllPreviousQuery() =
        queryDao.getPreviousQuery().map { list ->
            list.map {
                it.toDomain()
            }
        }
}