package com.loperilla.rawg.datasource.network.model

import com.loperilla.rawg.model.creator.CreatorPosition
import com.loperilla.rawg.model.interfaces.IRemoteResponse
import kotlinx.serialization.Serializable

@Serializable
data class CreatorPositionResponse(
    val id: Int,
    val name: String,
    val slug: String
) : IRemoteResponse<CreatorPosition>() {
    override fun toDomain() = CreatorPosition(
        id, name, slug
    )
}