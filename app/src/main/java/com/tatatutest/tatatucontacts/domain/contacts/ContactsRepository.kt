package com.tatatutest.tatatucontacts.domain.contacts

import com.tatatutest.tatatucontacts.domain.model.Contact
import com.tatatutest.tatatucontacts.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    suspend fun getAllContacts(): Flow<Result<List<Contact>>>
    suspend fun searchContacts(query: String): Flow<Result<List<Contact>>>
}
