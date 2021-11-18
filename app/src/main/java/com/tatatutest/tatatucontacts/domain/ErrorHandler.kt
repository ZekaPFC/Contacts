package com.tatatutest.tatatucontacts.domain

import com.tatatutest.tatatucontacts.domain.model.Error

interface ErrorHandler {
    fun getError(throwable: Throwable): Error
}
