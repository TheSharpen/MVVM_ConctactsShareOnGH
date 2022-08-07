package com.example.mvvm_conctacts.ui.contact_list

import android.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvm_conctacts.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun ContactListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ContactListViewModel = hiltViewModel()
) {
    val persons = viewModel.persons.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {

                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(ContactListEvent.OnUndoDeleteClick)
                    }
                }

                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(ContactListEvent.OnAddPersonClick)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add person")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()
        ) {
            items(persons.value) { person ->
                ContactItem(
                    person = person,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(ContactListEvent.OnPersonClick(person))
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}