package com.randrez.projectmarvel.data.source

import androidx.annotation.StringRes

sealed class ResourceResult<T>(
    val data: T? = null,
    @StringRes val messageResource: Int? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResourceResult<T>(data)
    class ErrorResource<T>(@StringRes message: Int?, data: T? = null) :
        ResourceResult<T>(data = data, messageResource = message)

    class Error<T>(message: String?, data: T? = null) : ResourceResult<T>(data = data, message = message)
    class Loading<T>(data: T? = null) : ResourceResult<T>(data)
}
