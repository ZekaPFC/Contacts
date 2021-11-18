package com.tatatutest.tatatucontacts.data.contacts

import com.tatatutest.tatatucontacts.domain.model.Contact
import com.tatatutest.tatatucontacts.domain.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface ContactsRemoteDS {
    @ExperimentalCoroutinesApi
    suspend fun getAllContacts(): Flow<Result<List<Contact>>>
}
