package com.tatatutest.tatatucontacts.data.contacts

import com.tatatutest.tatatucontacts.domain.model.Contact
import com.tatatutest.tatatucontacts.domain.model.Result
import com.tatatutest.tatatucontacts.app.utils.ioFlow
import com.tatatutest.tatatucontacts.app.utils.ioFlowWithResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ContactsLocalDSImpl @Inject constructor(private val contactsDao: ContactsDao) :
    ContactsLocalDS {

    override suspend fun getAllContacts(): Flow<Result<List<Contact>>> {
        return ioFlowWithResult { contactsDao.getAllContacts() }
    }

    override suspend fun searchContacts(query: String): Flow<Result<List<Contact>>> {
        return ioFlowWithResult { contactsDao.searchContacts("%$query%") }
    }

    override suspend fun insertContacts(contacts: List<Contact>) {
        ioFlow { contactsDao.insertContacts(contacts) }.collect()
    }
}
