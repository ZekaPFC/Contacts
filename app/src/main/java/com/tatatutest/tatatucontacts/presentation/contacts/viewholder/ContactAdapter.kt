package com.tatatutest.tatatucontacts.presentation.contacts.viewholder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tatatutest.tatatucontacts.R
import com.tatatutest.tatatucontacts.databinding.ContactChildViewHolderBinding
import com.tatatutest.tatatucontacts.databinding.ContactGroupListItemBinding
import com.tatatutest.tatatucontacts.domain.model.Contact
import com.tatatutest.tatatucontacts.presentation.contacts.ContactListVM
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class ContactAdapter(items: List<ContactExpandableGroup>) :
    ExpandableRecyclerViewAdapter<ContactGroupViewHolder, ContactChildViewHolder>(items) {
    override fun onCreateGroupViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): ContactGroupViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding =
            DataBindingUtil.inflate<ContactGroupListItemBinding>(
                inflater,
                R.layout.contact_group_list_item,
                parent,
                false
            )
        return ContactGroupViewHolder(binding)
    }

    override fun onCreateChildViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): ContactChildViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding =
            DataBindingUtil.inflate<ContactChildViewHolderBinding>(
                inflater,
                R.layout.contact_child_view_holder,
                parent,
                false
            )
        return ContactChildViewHolder(binding)
    }

    override fun onBindChildViewHolder(
        holder: ContactChildViewHolder?,
        flatPosition: Int,
        group: ExpandableGroup<*>?,
        childIndex: Int
    ) {
        val contact = group?.items?.get(childIndex) as ContactListVM
        holder?.bind(contact)
    }

    override fun onBindGroupViewHolder(
        holder: ContactGroupViewHolder?,
        flatPosition: Int,
        group: ExpandableGroup<*>?
    ) {
        val contactGroup = group as ContactExpandableGroup
        contactGroup.toggle()
        holder?.bind(contactGroup)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<ContactExpandableGroup>) {
        expandableList.groups = items
        expandableList.expandedGroupIndexes = BooleanArray(items.size)
        notifyDataSetChanged()
    }
}
