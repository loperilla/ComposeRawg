package com.loperilla.rawg.model.interfaces

abstract class IRemoteResponse<M : IModel> : IModel {
    abstract fun toDomain(): M
}