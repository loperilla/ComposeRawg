package com.loperilla.rawg.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.loperilla.rawg.model.game.Query
import com.loperilla.rawg.model.interfaces.IEntityModel

@Entity
data class QueryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val query: String
) : IEntityModel<Query>() {
    override fun toDomain() = Query(query)

}
