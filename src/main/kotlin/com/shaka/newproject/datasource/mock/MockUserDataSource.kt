package com.shaka.newproject.datasource.mock

import com.shaka.newproject.datasource.UserDataSource
import com.shaka.newproject.model.Users
import org.springframework.stereotype.Repository

@Repository
class MockUserDataSource : UserDataSource {
    var users = listOf(
        Users("1234", 3.14, 1),
        Users("5678", 6.28, 2),
        Users("9101", 9.42, 3)
    )
    override fun getUser(): Collection<Users> = users
}