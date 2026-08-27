package com.example.plaintext.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plaintext.data.dao.PasswordDao
import com.example.plaintext.data.model.Password

@Database(
    entities = [Password::class],
    version = 2,
    exportSchema = false
)
abstract class PlainTextDatabase : RoomDatabase() {
    abstract fun passwordDao(): PasswordDao
}
