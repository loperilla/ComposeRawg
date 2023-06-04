package com.loperilla.rawg.model.creator

import com.loperilla.rawg.model.interfaces.IModel

data class Creator(
    val creatorGame: List<CreatorGame>,
    val gamesCount: Int,
    val id: Int,
    val image: String,
    val imageBackground: String,
    val name: String,
    val creatorPosition: List<CreatorPosition>,
    val slug: String
) : IModel
