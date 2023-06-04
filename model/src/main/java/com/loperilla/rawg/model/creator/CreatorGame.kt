package com.loperilla.rawg.model.creator

import com.loperilla.rawg.model.interfaces.IModel

data class CreatorGame(
    val added: Int,
    val id: Int,
    val name: String,
    val slug: String
) : IModel
