package com.tatatutest.tatatucontacts.data

import com.tatatutest.tatatucontacts.domain.ErrorHandler
import com.tatatutest.tatatucontacts.domain.model.Error
import retrofit2.HttpException
import java.io.IOException

class ErrorHandlerImpl() : ErrorHandler {
    override fun getError(throwable: Throwable): Error {
        return when (throwable) {
            is IOException -> Error.NoConnection
            is HttpException -> Error.RequestError
            else -> Error.Unknown
        }
    }
}
