package com.example.a_common

sealed class ResultData<out T> {
    class Success<T>(val t: T) : ResultData<T>()
//    class Error<T>(val error: Throwable) : ResultData<T>()
    class Error<T>(val message: String? ="") : ResultData<T>()
    class Loading<T> : ResultData<T>()
}
