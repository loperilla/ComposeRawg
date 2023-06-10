package com.loperilla.rawg.model.game

import com.loperilla.rawg.model.interfaces.IModel

data class Game(
    val id: Long,
    val name: String,
    val image: String
) : IModel
