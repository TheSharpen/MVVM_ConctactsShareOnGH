package com.example.mvvm_conctacts.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("SELECT * FROM person WHERE id = :id")
    suspend fun getPersonById(id: Int): Person?

    @Query("SELECT * FROM person")
    fun getPersons(): Flow<List<Person>>
}