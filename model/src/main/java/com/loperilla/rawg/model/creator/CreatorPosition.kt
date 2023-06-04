package com.loperilla.rawg.model.creator

import com.loperilla.rawg.model.interfaces.IModel

data class CreatorPosition(
    val id: Int,
    val name: String,
    val slug: String
) : IModel
