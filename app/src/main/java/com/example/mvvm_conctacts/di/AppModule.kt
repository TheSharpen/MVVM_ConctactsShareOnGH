package com.example.mvvm_conctacts.di

import android.app.Application
import androidx.room.Room
import com.example.mvvm_conctacts.data.PersonDatabase
import com.example.mvvm_conctacts.data.PersonRepository
import com.example.mvvm_conctacts.data.PersonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePersonDatabase(app: Application): PersonDatabase {
        return Room.databaseBuilder(
            app,
            PersonDatabase::class.java,
            "person_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: PersonDatabase): PersonRepository {
        return PersonRepositoryImpl(db.dao)
    }

}