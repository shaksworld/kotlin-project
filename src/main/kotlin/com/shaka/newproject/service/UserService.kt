package com.shaka.newproject.service

import com.shaka.newproject.datasource.UserDataSource
import com.shaka.newproject.model.Users
import org.springframework.stereotype.Service

@Service
class UserService (private val userDataSource: UserDataSource) {

    fun getUser(): Collection<Users> = userDataSource.getUser()
}