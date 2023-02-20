package com.shaka.newproject.service

import com.shaka.newproject.datasource.UserDataSource
import com.shaka.newproject.datasource.mock.MockUserDataSource
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify

private val dataSource: UserDataSource = MockUserDataSource()

private val userService = UserService(dataSource)
internal class UserServiceTest {

    @Test
    fun `should return a collection of users`() {

        val users = userService.getUser()

        // assert that the collection is not empty
        assert(users.isNotEmpty())
    }
}

