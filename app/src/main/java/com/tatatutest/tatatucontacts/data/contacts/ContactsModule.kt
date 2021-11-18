package com.tatatutest.tatatucontacts.data.contacts

import com.tatatutest.tatatucontacts.domain.contacts.ContactsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(ViewModelComponent::class)
abstract class ContactsModule {
    @Binds
    abstract fun bindContactsRepository(contactsRepoImpl: ContactsRepoImpl): ContactsRepository

    @Binds
    abstract fun bindContactsRemoteDS(contactsRemoteDSImpl: ContactsRemoteDSImpl): ContactsRemoteDS

    @Binds
    abstract fun bindContactsLocalDS(contactsLocalDSImpl: ContactsLocalDSImpl): ContactsLocalDS
}
