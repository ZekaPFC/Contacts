package com.tatatutest.tatatucontacts.domain.model

sealed class Error {

    object NoConnection : Error()

    object RequestError : Error()

    object Unknown : Error()
}
