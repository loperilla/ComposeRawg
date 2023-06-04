package com.loperilla.rawg.model.creator

import com.loperilla.rawg.model.interfaces.IModel

data class CreatorRole(
    val id: Int,
    val name: String,
    val slug: String
) : IModel
