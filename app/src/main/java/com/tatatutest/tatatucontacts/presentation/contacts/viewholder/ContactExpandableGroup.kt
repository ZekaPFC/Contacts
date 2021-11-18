package com.tatatutest.tatatucontacts.presentation.contacts.viewholder

import com.tatatutest.tatatucontacts.presentation.contacts.ContactListVM
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class ContactExpandableGroup(
    val name: String?,
    val username: String?,
    items: MutableList<ContactListVM>?
) : ExpandableGroup<ContactListVM>(name, items) {
    private var expanded: Boolean = true
    fun toggle() {
        expanded = !expanded
    }

    fun isExpanded(): Boolean {
        return expanded
    }
}
