package com.loperilla.rawg.datasource.network.model

import com.loperilla.rawg.model.creator.CreatorGame
import com.loperilla.rawg.model.interfaces.IRemoteResponse
import kotlinx.serialization.Serializable

@Serializable
data class CreatorGameResponse(
    val added: Int,
    val id: Int,
    val name: String,
    val slug: String
) : IRemoteResponse<CreatorGame>() {
    override fun toDomain() = CreatorGame(
        added, id, name, slug
    )
}