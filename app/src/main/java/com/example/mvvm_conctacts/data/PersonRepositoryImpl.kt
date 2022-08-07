package com.example.mvvm_conctacts.data

import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl(
    private val dao: PersonDao
    ): PersonRepository {

    override suspend fun insertPerson(person: Person) {
        dao.insertPerson(person)
    }


    override suspend fun deletePerson(person: Person) {
        dao.deletePerson(person)
    }

    override suspend fun getPersonById(id: Int): Person? {
        return dao.getPersonById(id)
    }


    override fun getPersons(): Flow<List<Person>> {
        return dao.getPersons()
    }

}