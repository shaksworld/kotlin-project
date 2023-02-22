package com.shaka.newproject.datasource

import com.shaka.newproject.model.Users
import org.springframework.stereotype.Service

@Service
interface UserDataSource {

    fun retriveUser(): Collection<Users>
    fun workUser(name: String): Users
    fun addUser(users: Users): Users
    fun updateUser(users: Users): Users

    fun deleteUser(name: String)


}