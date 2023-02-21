package com.shaka.newproject.service

import com.shaka.newproject.datasource.UserDataSource
import com.shaka.newproject.model.Users
import org.springframework.stereotype.Service

@Service
class UserService (private val userDataSource: UserDataSource) {

    fun getUser(): Collection<Users> = userDataSource.retriveUser()

    fun getUser(name: String): Users = userDataSource.workUser(name)
    fun createUser(users: Users): Any {
        TODO("Not yet implemented")
    }

//    fun getUser(accountNumber: String): Users = userDataSource.workUser(accountNumber)




}