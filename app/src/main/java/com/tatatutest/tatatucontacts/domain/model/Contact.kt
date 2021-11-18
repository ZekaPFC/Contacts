package com.tatatutest.tatatucontacts.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey val id: Int,
    val username: String?,
    val name: String?,
    val email: String?,
    val phone: String?,
    val website: String?,
    @Embedded val address: Address?,
    @Embedded val company: Company?
) 
