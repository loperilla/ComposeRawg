package com.loperilla.rawg.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.loperilla.rawg.model.game.detail.Genre
import com.loperilla.rawg.model.interfaces.IEntityModel

@Entity
data class GenreEntity(
    @PrimaryKey
    val id: Long,
    val name: String
): IEntityModel<Genre>() {
    override fun toDomain() = Genre(
        id, name
    )
}
