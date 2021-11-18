package com.tatatutest.tatatucontacts.presentation.contacts.viewholder

import com.tatatutest.tatatucontacts.databinding.ContactChildViewHolderBinding
import com.tatatutest.tatatucontacts.domain.model.Contact
import com.tatatutest.tatatucontacts.presentation.contacts.ContactListVM
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder

class ContactChildViewHolder(private val binding: ContactChildViewHolderBinding) :
    ChildViewHolder(binding.root) {

    fun bind(item: ContactListVM) {
        binding.contact = item
        binding.executePendingBindings()
    }
}
