package com.loperilla.rawg.datasource.network.model.game

import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotResponse(
    val id: Int,
    val image: String
)