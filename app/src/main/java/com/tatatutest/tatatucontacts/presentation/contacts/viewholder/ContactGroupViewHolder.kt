package com.tatatutest.tatatucontacts.presentation.contacts.viewholder

import com.tatatutest.tatatucontacts.databinding.ContactGroupListItemBinding
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder

class ContactGroupViewHolder(private val binding: ContactGroupListItemBinding) :
    GroupViewHolder(binding.root) {

    fun bind(group: ContactExpandableGroup) {
        binding.group = group
        binding.executePendingBindings()
    }
}
