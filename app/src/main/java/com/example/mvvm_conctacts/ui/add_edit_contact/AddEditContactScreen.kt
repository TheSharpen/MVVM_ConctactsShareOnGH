package com.example.mvvm_conctacts.ui.add_edit_contact

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvm_conctacts.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun AddEditContactScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditContactViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack  -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold (
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditContactEvent.OnSavePersonClick)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Save" )
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f),
            contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person icon",
                    modifier = Modifier.fillMaxSize(0.75f)
                )

            }
            
            TextField(value = viewModel.name, onValueChange = {
                viewModel.onEvent(AddEditContactEvent.OnNameChange(it))
            },
            placeholder = {
                Text(text = "Name")
            },
            modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = viewModel.surname, onValueChange = {
                viewModel.onEvent(AddEditContactEvent.OnSurnameChange(it))
            },
                placeholder = {
                    Text(text = "Surname")
                },
                modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = viewModel.address, onValueChange = {
                viewModel.onEvent(AddEditContactEvent.OnAddressChange(it))
            },
                placeholder = {
                    Text(text = "Address")
                },
                modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = viewModel.city, onValueChange = {
                viewModel.onEvent(AddEditContactEvent.OnCityChange(it))
            },
                placeholder = {
                    Text(text = "City")
                },
                modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = viewModel.psc, onValueChange = {
                viewModel.onEvent(AddEditContactEvent.OnPscChange(it))
            },
                placeholder = {
                    Text(text = "PSC")
                },
                modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = viewModel.phone, onValueChange = {
                viewModel.onEvent(AddEditContactEvent.OnPhoneChange(it))
            },
                placeholder = {
                    Text(text = "Phone")
                },
                modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
    

}