package com.example.myapplication.data.utils

// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
    val data: T? = null,
    val errorCode: String? = null,
    val message: String? = null
) {
    class ApiSuccess<T>(data: T) : Resource<T>(data)
    class ApiSuccessError<T>(data: T? = null) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, errorCode: String? = null, data: T? = null) :
        Resource<T>(data, errorCode, message)
}
