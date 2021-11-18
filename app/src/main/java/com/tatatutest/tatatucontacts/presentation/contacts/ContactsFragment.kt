package com.tatatutest.tatatucontacts.presentation.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tatatutest.tatatucontacts.R
import com.tatatutest.tatatucontacts.databinding.ContactsFragmentBinding
import com.tatatutest.tatatucontacts.domain.model.Error
import com.tatatutest.tatatucontacts.domain.model.Result
import com.tatatutest.tatatucontacts.presentation.contacts.viewholder.ContactAdapter
import com.tatatutest.tatatucontacts.presentation.contacts.viewholder.ContactExpandableGroup
import com.tatatutest.tatatucontacts.app.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : Fragment() {
    private val contactsViewModel: ContactsViewModel by viewModels()
    private lateinit var binding: ContactsFragmentBinding
    private val adapter = ContactAdapter(listOf())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.contacts_fragment,
                container,
                false
            )
        bindView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeContacts()
        loadContacts()
    }

    private fun bindView() {
        binding.recyclerView.adapter = adapter
        binding.viewModel = contactsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun observeContacts() {
        contactsViewModel.contactsLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> displayContacts(result.data)
                is Result.Error -> handleError(result.exception)
            }
        }
    }

    private fun loadContacts() {
        contactsViewModel.getAllContacts()
    }

    private fun displayContacts(contacts: List<ContactExpandableGroup>) {
        adapter.setItems(contacts)
    }

    private fun handleError(exception: Error) {
        when (exception) {
            is Error.NoConnection -> toast(getString(R.string.no_internet_connection))
            is Error.RequestError -> toast(getString(R.string.request_error))
            is Error.Unknown -> toast(getString(R.string.unknown_error))
        }
    }
}
