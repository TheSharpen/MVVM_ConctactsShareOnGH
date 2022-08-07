package com.example.mvvm_conctacts.ui.contact_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm_conctacts.data.Person

@Composable
fun ContactItem(
    person: Person,
    onEvent: (ContactListEvent) -> Unit,
    modifier: Modifier = Modifier) {

    Card(
        backgroundColor = Color.White,
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {


    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.Center) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Person" )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = (person.name + " " + person.surname),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    onEvent(ContactListEvent.OnDeletePersonClick(person))
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete person")
                }
            }
        }
    }

    }

}