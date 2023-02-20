package com.shaka.newproject.datasource.mock

import com.shaka.newproject.model.Users
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockUserDataSourceTest {

    private val mockUserDataSource = MockUserDataSource()

    @Test
    fun `should return a collection of users`() {
        val users = mockUserDataSource.getUser()

        // assert that the collection is not empty
        assert(users.isNotEmpty())
//        Assertions.assertThat(users).isNotEmpty
    }

    @Test
    fun `should return the correct data for the user`() {
        val users = mockUserDataSource.getUser()

        // assert that the data is correct
//        assert(users.contains(Users("1234", 3.14, 1)))
        assert(users.all { it.accountNumber.isNotBlank() })
        assert(users.all { it.trust != 0.0 })
        assert(users.any { it.transactionFee != 0 })
    }
}