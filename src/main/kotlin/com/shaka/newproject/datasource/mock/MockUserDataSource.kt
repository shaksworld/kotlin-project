package com.shaka.newproject.datasource.mock

import com.shaka.newproject.datasource.UserDataSource
import com.shaka.newproject.model.Users
import org.springframework.stereotype.Repository

@Repository
class MockUserDataSource : UserDataSource {
    var users = listOf(
        Users("Shaka", 1234, "Kigali"),
        Users("Sofiyyah", 1235, "Kigali"),
        Users("Usman", 1236, "Kigali"),
    )
    override fun retriveUser(): Collection<Users> = users
    override fun workUser(accountNumber: String): Users {
        TODO("Not yet implemented")
    }


//    override fun workUser(accountNumber: String): Users {
//        return users.firstOrNull() { it.name == accountNumber }
//            ?: throw NoSuchElementException("Could not find a user with account number $accountNumber")
//    }
}