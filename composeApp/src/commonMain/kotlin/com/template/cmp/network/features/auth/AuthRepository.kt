package com.template.cmp.network.features.auth

import com.template.cmp.core.network.auth.AuthApi
import com.template.cmp.database.PeopleDao
import com.template.cmp.database.Person

class AuthRepository(
    private val authApi: AuthApi,
    private val peopleDao: PeopleDao,
) {

    suspend fun auth() {
        val authResult = authApi.mockLogin()
    }

    suspend fun addPerson() {
        peopleDao.upsert(Person("123"))
    }
}
