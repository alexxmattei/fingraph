package com.example.fingraph.models.base

sealed class Result<out S, out E> where S : Any?, E : Exception{
    data class Success<out S>(val value: S) : Result<S, Nothing>()
    data class Error<out E: Exception>(val value: E) : Result<Nothing, E>()

    fun handleResult(fnError: (E) -> Unit, fnSuccess: ((S) -> Unit)? = null) {
        when(this) {
            is Error -> fnError(value)
            is Success -> fnSuccess?.invoke(value)
        }
    }

    fun getOrThrow(): S {
        return when (this) {
            is Error -> throw this.value
            is Success -> this.value
        }
    }
}