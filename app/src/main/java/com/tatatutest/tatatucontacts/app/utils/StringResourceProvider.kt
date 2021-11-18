package com.tatatutest.tatatucontacts.app.utils

import android.content.Context

class StringResourceProvider(private val context: Context) {
    fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}
