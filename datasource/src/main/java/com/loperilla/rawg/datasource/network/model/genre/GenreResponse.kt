package com.loperilla.rawg.datasource.network.model.genre

import com.loperilla.rawg.datasource.database.entity.GenreEntity
import com.loperilla.rawg.model.game.detail.Genre
import com.loperilla.rawg.model.interfaces.IRemoteResponse
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    val id: Long,
    val name: String
): IRemoteResponse<GenreEntity, Genre>() {
    override fun toDomain() = Genre(id, name)

    override fun toEntity() = GenreEntity(id, name)
}