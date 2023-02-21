package com.shaka.newproject.datasource.mock

import com.shaka.newproject.datasource.UserDataSource
import com.shaka.newproject.model.Users
import org.springframework.stereotype.Repository

@Repository("mock")
class MockUserDataSource : UserDataSource {
    var users = mutableListOf(
        Users("shaka", 123456789, "Nigeria"),
        Users("sofiyyah", 123456789, "Ghana"),
        Users("shakirat", 123456789, "Nigeria")
    )
    override fun retriveUser(): Collection<Users> = users
    override fun workUser(name: String): Users = users.firstOrNull() { it.name == name }
        ?: throw NoSuchElementException("Could not find a user with name $name")

    override fun addUser(users: Users): Users {
//        if (users.name in users.name)
//            throw IllegalArgumentException("User with name ${users.name} already exists")
        this.users.add(users)
        return users
    }

    override fun updateUser(users: Users): Users {
        val currentUsers = users.firstOrNull { it.name == users.name }
            ?: throw NoSuchElementException("Could not find a user with name ${users.name}")
        users.remove(currentUsers)
        users.add(users)

        return users
    }
}