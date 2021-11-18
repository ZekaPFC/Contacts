package com.tatatutest.tatatucontacts.data.contacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tatatutest.tatatucontacts.domain.model.Contact

@Dao
interface ContactsDao {
    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<Contact>

    @Query("SELECT * FROM contacts WHERE name LIKE :query OR email LIKE :query ")
    suspend fun searchContacts(query: String): List<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts: List<Contact>)
}
