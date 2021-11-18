package com.tatatutest.tatatucontacts.data.contacts

import com.tatatutest.tatatucontacts.domain.model.Contact
import com.tatatutest.tatatucontacts.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface ContactsLocalDS {
    suspend fun getAllContacts(): Flow<Result<List<Contact>>>
    suspend fun searchContacts(query: String): Flow<Result<List<Contact>>>
    suspend fun insertContacts(contacts: List<Contact>)
}
