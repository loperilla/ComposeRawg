package com.loperilla.rawg.datasource.network.model.game

import kotlinx.serialization.Serializable

@Serializable
data class StoresResponse(
    val store: StoreResponse
)