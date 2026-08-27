package com.example.plaintext.data.di

import android.content.Context
import androidx.room.Room
import com.example.plaintext.data.PlainTextDatabase
import com.example.plaintext.data.Migrations.MIGRATION_1_2
import com.example.plaintext.data.dao.PasswordDao
import com.example.plaintext.data.repository.LocalPasswordDBStore
import com.example.plaintext.data.repository.PasswordDBStore
import com.example.plaintext.ui.screens.hello.dbSimulator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDiModule {
    @Provides
    @Singleton
    fun providePlainTextDatabase(
        @ApplicationContext context: Context
    ): PlainTextDatabase = Room.databaseBuilder(
        context,
        PlainTextDatabase::class.java,
        "plaintext-database"
    ).addMigrations(MIGRATION_1_2).build()

    @Provides
    @Singleton
    fun providePasswordDao(
        database: PlainTextDatabase
    ): PasswordDao = database.passwordDao()

    @Provides
    @Singleton
    fun providePasswordDBStore(
        passwordDao: PasswordDao
    ): PasswordDBStore = LocalPasswordDBStore(passwordDao)

    @Provides
    @Singleton
    fun provideDBSimulator(): dbSimulator = dbSimulator()
}
