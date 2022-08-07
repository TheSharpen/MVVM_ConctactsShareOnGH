package com.example.mvvm_conctacts.ui.add_edit_contact

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_conctacts.data.Person
import com.example.mvvm_conctacts.data.PersonRepository
import com.example.mvvm_conctacts.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditContactViewModel @Inject constructor(
    private val repository: PersonRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var person by mutableStateOf<Person?>(null)
    private set

        var name by mutableStateOf("")
        private set

    var surname by mutableStateOf("")
    private set

    var address by mutableStateOf("")
    private set

    var city by mutableStateOf("")
    private set

    var psc by mutableStateOf("")
    private set

    var phone by mutableStateOf("")
    private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val personId = savedStateHandle.get<Int>("personId")!!
        if (personId != -1) {
            viewModelScope.launch {
                repository.getPersonById(personId)?.let {
                    person ->
                    name = person.name
                    surname = person.surname
                    address = person.address
                    city = person.city
                    psc = person.psc
                    phone = person.phone
                    this@AddEditContactViewModel.person = person
                }
            }
        }
    }

    fun onEvent(event: AddEditContactEvent) {
        when (event) {

            is AddEditContactEvent.OnNameChange -> {
                name = event.name
            }

            is AddEditContactEvent.OnSurnameChange -> {
                surname = event.surname
            }

            is AddEditContactEvent.OnAddressChange -> {
                address = event.address
            }

            is AddEditContactEvent.OnCityChange -> {
                city = event.city
            }

            is AddEditContactEvent.OnPscChange -> {
                psc = event.psc
            }

            is AddEditContactEvent.OnPhoneChange -> {
                phone = event.phone
            }

            is AddEditContactEvent.OnSavePersonClick -> {
                viewModelScope.launch {
                    if (name.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The name can't be empty"
                        ))
                        return@launch
                    }

                    if (surname.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The surname can't be empty"
                        ))
                        return@launch
                    }

                    if (address.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The address can't be empty"
                        ))
                        return@launch
                    }

                    if (city.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The city can't be empty"
                        ))
                        return@launch
                    }

                    if (psc.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The psc can't be empty"
                        ))
                        return@launch
                    }

                    if (phone.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The phone can't be empty"
                        ))
                        return@launch
                    }

                    repository.insertPerson(
                        Person(
                            name = name,
                            surname = surname,
                            address = address,
                            city = city,
                            psc = psc,
                            phone = phone,
                            id =   person?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }



    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
