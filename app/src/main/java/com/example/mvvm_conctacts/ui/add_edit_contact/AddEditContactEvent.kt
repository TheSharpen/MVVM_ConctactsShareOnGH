package com.example.mvvm_conctacts.ui.add_edit_contact

sealed class AddEditContactEvent {
    data class OnNameChange(val name: String): AddEditContactEvent()
    data class OnSurnameChange(val surname: String): AddEditContactEvent()
    data class OnAddressChange(val address: String): AddEditContactEvent()
    data class OnCityChange(val city: String): AddEditContactEvent()
    data class OnPscChange(val psc: String): AddEditContactEvent()
    data class OnPhoneChange(val phone: String): AddEditContactEvent()
    object OnSavePersonClick: AddEditContactEvent()
}

