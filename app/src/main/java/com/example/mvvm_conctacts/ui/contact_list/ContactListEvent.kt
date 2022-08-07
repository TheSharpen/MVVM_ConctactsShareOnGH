package com.example.mvvm_conctacts.ui.contact_list

import com.example.mvvm_conctacts.data.Person

sealed class ContactListEvent {
    data class OnDeletePersonClick(val person: Person): ContactListEvent()
    object OnUndoDeleteClick: ContactListEvent()
    data class OnPersonClick(val person: Person): ContactListEvent()
    object OnAddPersonClick: ContactListEvent()
}
