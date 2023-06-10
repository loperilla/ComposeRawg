package com.loperilla.rawg.datasource.network.model.game

import kotlinx.serialization.Serializable

@Serializable
data class PlatformResponse(
    val id: Int,
    val name: String,
    val slug: String
)