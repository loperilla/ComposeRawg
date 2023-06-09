package com.loperilla.rawg.model.interfaces

abstract class IEntityModel<M: IModel>{
    abstract fun toDomain(): M
}