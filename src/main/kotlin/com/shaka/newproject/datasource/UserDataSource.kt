package com.shaka.newproject.datasource

import com.shaka.newproject.model.Users

interface UserDataSource {


    fun retriveUser(): Collection<Users>
    fun workUser(name: String): Users
    fun addUser(users: Users): Users
    fun updateUser(users: Users): Users

    fun deleteUser(name: String)


}