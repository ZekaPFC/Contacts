package com.tatatutest.tatatucontacts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tatatutest.tatatucontacts.data.contacts.ContactsDao
import com.tatatutest.tatatucontacts.domain.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
}
