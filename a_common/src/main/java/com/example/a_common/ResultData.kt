package com.example.a_common

sealed class ResultData<out T> {
    class Success<T> : ResultData<T>()
    class Error<T>(val error: String) : ResultData<T>()
    class Message<T>(val message: String? ="") : ResultData<T>()
    class Loading<T> : ResultData<T>()
}
