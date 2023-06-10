package com.loperilla.rawg.model.interfaces

abstract class IRemoteResponse<E : IEntityModel<M>, M : IModel> : IModel {

    abstract fun toDomain(): M
    abstract fun toEntity(): E
}

abstract class IRemoteModelResponse<M : IModel> : IModel {
    abstract fun toDomain(): M
}