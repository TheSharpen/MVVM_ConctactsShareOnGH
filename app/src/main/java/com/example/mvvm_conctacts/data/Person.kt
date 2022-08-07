package com.example.mvvm_conctacts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    val name: String,
    val surname: String,
    val address: String,
    val city: String,
    val psc: String,
    val phone: String,
    @PrimaryKey val id: Int? = null
)