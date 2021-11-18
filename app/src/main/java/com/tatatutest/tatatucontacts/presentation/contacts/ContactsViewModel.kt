package com.tatatutest.tatatucontacts.presentation.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatatutest.tatatucontacts.R
import com.tatatutest.tatatucontacts.app.utils.StringResourceProvider
import com.tatatutest.tatatucontacts.domain.contacts.ContactsRepository
import com.tatatutest.tatatucontacts.domain.model.Contact
import com.tatatutest.tatatucontacts.domain.model.Result
import com.tatatutest.tatatucontacts.presentation.contacts.viewholder.ContactExpandableGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactsRepository: ContactsRepository,
    private val stringResourceProvider: StringResourceProvider
) :
    ViewModel() {
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading
    private val _contactsMutableLiveData: MutableLiveData<Result<List<ContactExpandableGroup>>> =
        MutableLiveData()
    val contactsLiveData: LiveData<Result<List<ContactExpandableGroup>>> = _contactsMutableLiveData

    fun getAllContacts() {
        viewModelScope.launch {
            startLoading()
            loadContacts()
            stopLoading()
        }
    }

    fun searchContacts(query: String) {
        viewModelScope.launch {
            startLoading()
            search(query)
            stopLoading()
        }
    }

    private suspend fun loadContacts() {
        contactsRepository.getAllContacts().map { item -> mapResult(item) }.collect {
            postResult(it)
        }
    }

    private suspend fun search(query: String) {
        contactsRepository.searchContacts(query).map { item -> mapResult(item) }
            .collect { postResult(it) }
    }

    private fun mapResult(result: Result<List<Contact>>): Result<List<ContactExpandableGroup>> {
        return when (result) {
            is Result.Success -> mapContactIntoGroup(result.data)
            is Result.Error -> Result.Error(result.exception)
        }
    }

    private fun mapContactIntoGroup(contacts: List<Contact>): Result<List<ContactExpandableGroup>> {
        val groups = mutableListOf<ContactExpandableGroup>()
        contacts.forEach { contact ->
            val username =
                "${stringResourceProvider.getString(R.string.username)}: ${contact.username}"
            val name = "${stringResourceProvider.getString(R.string.name)}: ${contact.name}"
            val email = "${stringResourceProvider.getString(R.string.email)}: ${contact.email}"
            val phone = "${stringResourceProvider.getString(R.string.phone)}: ${contact.phone}"
            val website =
                "${stringResourceProvider.getString(R.string.website)}: ${contact.website}"
            val address =
                "${stringResourceProvider.getString(R.string.address)}: ${contact.address?.fullAddress}"
            val companyName =
                "${stringResourceProvider.getString(R.string.company_name)}: ${contact.company?.name}"
            val contactVm =
                ContactListVM(username, name, email, address, companyName, website, phone)
            val group =
                ContactExpandableGroup(name, username, mutableListOf(contactVm))
            groups.add(group)
        }
        return Result.Success(groups)
    }

    private fun postResult(result: Result<List<ContactExpandableGroup>>) {
        _contactsMutableLiveData.postValue(result)
    }

    private fun startLoading() {
        _loading.postValue(true)
    }

    private fun stopLoading() {
        _loading.postValue(false)
    }
}
