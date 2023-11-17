package com.felix.chucknorrisfact.core.util

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    object Loading : ResultState<Nothing>()
    data class Error(val exception: Throwable) : ResultState<Nothing>()
}