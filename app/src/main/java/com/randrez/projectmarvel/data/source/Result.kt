package com.randrez.projectmarvel.data.source

import androidx.annotation.StringRes

sealed class Result<T>(val data: T? = null, @StringRes val message: Int? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(@StringRes message: Int?, data: T? = null) : Result<T>(data, message)
    class Loading<T>(data: T? = null) : Result<T>(data)
}
