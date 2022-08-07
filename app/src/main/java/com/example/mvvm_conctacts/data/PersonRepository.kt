package com.example.mvvm_conctacts.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    suspend fun insertPerson(person: Person)


    suspend fun deletePerson(person: Person)


    suspend fun getPersonById(id: Int): Person?


    fun getPersons(): Flow<List<Person>>
}