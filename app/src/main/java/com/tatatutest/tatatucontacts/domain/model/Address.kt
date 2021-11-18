package com.tatatutest.tatatucontacts.domain.model

data class Address(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?
) {
    val fullAddress: String
        get() = "$suite, $street, $zipcode, $city"
}
