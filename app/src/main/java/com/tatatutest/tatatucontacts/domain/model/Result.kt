package com.tatatutest.tatatucontacts.domain.model

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: com.tatatutest.tatatucontacts.domain.model.Error) : Result<Nothing>()
}
