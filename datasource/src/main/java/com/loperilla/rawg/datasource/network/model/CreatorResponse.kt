package com.loperilla.rawg.datasource.network.model

import com.loperilla.rawg.model.creator.Creator
import com.loperilla.rawg.model.interfaces.IRemoteResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatorResponse(
    @SerialName("id") val id: Int,
    @SerialName("games") val creatorGame: List<CreatorGameResponse>,
    @SerialName("slug") val slug: String,
    @SerialName("games_count") val games_count: Int,
    @SerialName("image") val image: String,
    @SerialName("image_background") val image_background: String,
    @SerialName("name") val name: String,
    @SerialName("positions") val positions: List<CreatorPositionResponse>
) : IRemoteResponse<Creator>() {
    override fun toDomain() = Creator(
        creatorGame.map {
            it.toDomain()
        },
        games_count,
        id,
        image,
        image_background,
        name,
        positions.map {
            it.toDomain()
        },
        slug
    )
}