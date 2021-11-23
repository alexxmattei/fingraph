package com.example.fingraph.models.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

typealias NoParams = Unit
typealias NoResult = Unit

abstract class UseCase<in Params, out Type>(protected val dispatcher: CoroutineDispatcher) where Type : Any? {
    protected abstract suspend fun execute(params: Params): Type
    open suspend operator fun invoke(params: Params): Result<Type, Exception> = withContext(dispatcher) {
        try {
            val result = execute(params)
            Result.Success(result)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}