package com.example.plaintext.data.repository

import com.example.plaintext.data.dao.PasswordDao
import com.example.plaintext.data.model.Password
import com.example.plaintext.data.model.PasswordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PasswordDBStore {
    fun getList(): Flow<List<Password>>
    suspend fun add(password: Password): Long
    suspend fun update(password: Password)
    fun get(id: Int): Password?
    suspend fun save(passwordInfo: PasswordInfo)
    suspend fun isEmpty(): Flow<Boolean>
}

class LocalPasswordDBStore(
    private val passwordDao: PasswordDao
) : PasswordDBStore {
    override fun getList(): Flow<List<Password>> = passwordDao.getAll()

    override suspend fun add(password: Password): Long = passwordDao.insert(password)

    override suspend fun update(password: Password) = passwordDao.update(password)

    override fun get(id: Int): Password? = passwordDao.getById(id)

    override suspend fun save(passwordInfo: PasswordInfo) {
        val password: Password by passwordInfo
        if (password.id == 0) {
            passwordDao.insert(password)
        } else {
            passwordDao.update(password)
        }
    }

    override suspend fun isEmpty(): Flow<Boolean> =
        passwordDao.count().map { it == 0 }
}