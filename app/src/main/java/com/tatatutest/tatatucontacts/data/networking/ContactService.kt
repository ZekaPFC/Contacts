package com.tatatutest.tatatucontacts.data.networking

import com.tatatutest.tatatucontacts.domain.model.Contact
import retrofit2.http.GET

interface ContactService {
    @GET("users")
    suspend fun getContacts(): List<Contact>
}
