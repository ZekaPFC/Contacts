package com.tatatutest.tatatucontacts.data.contacts

import com.tatatutest.tatatucontacts.data.networking.ContactService
import com.tatatutest.tatatucontacts.domain.model.Contact
import com.tatatutest.tatatucontacts.domain.model.Result
import com.tatatutest.tatatucontacts.app.utils.ioFlowWithResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsRemoteDSImpl @Inject constructor(private val contactService: ContactService) :
    ContactsRemoteDS {
    @ExperimentalCoroutinesApi
    override suspend fun getAllContacts(): Flow<Result<List<Contact>>> {
        return ioFlowWithResult { contactService.getContacts() }
    }
}
