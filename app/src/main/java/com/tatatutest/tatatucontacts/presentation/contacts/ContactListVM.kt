package com.tatatutest.tatatucontacts.presentation.contacts

import android.os.Parcel
import android.os.Parcelable

data class ContactListVM(
    val username: String?,
    val name: String?,
    val email: String?,
    val address: String?,
    val companyName: String?,
    val website: String?,
    val phone: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(address)
        parcel.writeString(companyName)
        parcel.writeString(website)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactListVM> {
        override fun createFromParcel(parcel: Parcel): ContactListVM {
            return ContactListVM(parcel)
        }

        override fun newArray(size: Int): Array<ContactListVM?> {
            return arrayOfNulls(size)
        }
    }
}
