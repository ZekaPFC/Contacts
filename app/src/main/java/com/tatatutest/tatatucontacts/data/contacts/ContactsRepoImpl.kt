package com.tatatutest.tatatucontacts.data.contacts

import com.tatatutest.tatatucontacts.domain.contacts.ContactsRepository
import com.tatatutest.tatatucontacts.domain.model.Contact
import com.tatatutest.tatatucontacts.domain.model.Result
import com.tatatutest.tatatucontacts.app.utils.ioFlow
import com.tatatutest.tatatucontacts.app.utils.ioFlowWithResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ContactsRepoImpl @Inject constructor(
    private val localDS: ContactsLocalDS,
    private val remoteDS: ContactsRemoteDS
) : ContactsRepository {
    override suspend fun getAllContacts(): Flow<Result<List<Contact>>> {
        val localDBContacts = getContactsFromDB()
        val showLocalContacts = shouldShowLocalContacts(localDBContacts)
        return if (showLocalContacts) {
            ioFlowWithResult { localDBContacts }
        } else {
            ioFlow { getContactsFromNetwork() }
        }
    }

    override suspend fun searchContacts(query: String): Flow<Result<List<Contact>>> {
        return if (query.isNotEmpty()) {
            localDS.searchContacts(query)
        } else {
            localDS.getAllContacts()
        }
    }

    private suspend fun getContactsFromDB(): List<Contact> {
        return when (val result = localDS.getAllContacts().single()) {
            is Result.Success -> result.data
            else -> listOf()
        }
    }

    private fun shouldShowLocalContacts(localContacts: List<Contact>): Boolean {
        return localContacts.isNotEmpty()
    }

    private suspend fun getContactsFromNetwork(): Result<List<Contact>> {
        return when (val result = remoteDS.getAllContacts().single()) {
            is Result.Success -> {
                insertContacts(result.data)
                return result
            }
            is Result.Error -> Result.Error(result.exception)
        }
    }

    private suspend fun insertContacts(contacts: List<Contact>) {
        localDS.insertContacts(contacts)
    }
}
