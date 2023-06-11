package com.loperilla.rawg.datasource.network.model.game

import com.loperilla.rawg.datasource.network.model.genre.GenreResponse
import com.loperilla.rawg.model.game.Game
import com.loperilla.rawg.model.interfaces.IRemoteModelResponse
import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    val id: Long,
    val name: String,
    val added: Int,
    val short_screenshots: List<ScreenshotResponse>,
    val genres: List<GenreResponse>,
    val background_image: String?,
//    val community_rating: Int?,
    val rating: Double,
    val released: String,
    val reviews_count: Int,
    val reviews_text_count: Int,
//    val score: String?,
    val slug: String,
//    val storesResponses: List<StoresResponse>?,
    val tba: Boolean,
    val updated: String,
) : IRemoteModelResponse<Game>() {
    override fun toDomain() = Game(
        id, name, background_image ?: ""
    )

}