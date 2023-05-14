package com.example.common

sealed class ResultData<out T> {
    class Success<T>(val t: T) : ResultData<T>()
    class Error<T>(val error: Throwable) : ResultData<T>()
    class Message(val message: String) : ResultData<String>()
    class Loading<T> : ResultData<T>()
}
