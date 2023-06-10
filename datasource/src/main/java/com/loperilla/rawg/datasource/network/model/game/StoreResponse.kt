package com.loperilla.rawg.datasource.network.model.game

import kotlinx.serialization.Serializable

@Serializable
data class StoreResponse(
    val id: Int,
    val name: String,
    val slug: String
)