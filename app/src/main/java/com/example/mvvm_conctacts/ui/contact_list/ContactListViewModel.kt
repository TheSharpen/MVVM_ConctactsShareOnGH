package com.example.mvvm_conctacts.ui.contact_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvm_conctacts.data.Person
import com.example.mvvm_conctacts.data.PersonRepository
import com.example.mvvm_conctacts.util.Routes
import com.example.mvvm_conctacts.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val repository: PersonRepository
): ViewModel() {

    val persons = repository.getPersons()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedPerson: Person? = null

    fun onEvent(event: ContactListEvent) {
        when (event) {

            is ContactListEvent.OnPersonClick -> {
                        sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_CONTACT + "?personId=${event.person.id}"))
            }

            is ContactListEvent.OnAddPersonClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_CONTACT))
            }

            is ContactListEvent.OnUndoDeleteClick -> {
                deletedPerson?.let { person ->
                    viewModelScope.launch {
                        repository.insertPerson(person)
                    }
                }
            }

            is ContactListEvent.OnDeletePersonClick -> {
                viewModelScope.launch {
                    deletedPerson = event.person
                    repository.deletePerson(event.person)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Person data has been deleted",
                        action = "Undo"
                    ))
                }
            }


        }
    }



    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch{
            _uiEvent.send(event)
        }
    }
}