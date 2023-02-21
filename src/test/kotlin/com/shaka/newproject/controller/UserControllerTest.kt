package com.shaka.newproject.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.Lifecycle
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class UserControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("getUsers()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetUsers {

        @Test
        fun `should return all user`() {

            mockMvc.get("/user")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") { value("1234") }
                }
        }
    }

    @Nested
    @DisplayName("getUsers()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetUserByAccountNumber {

        @Test
        fun `should return user by account number`() {

            //given
            val accountNumber = "1234"

            //when
            mockMvc.get("/user/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value(3.14) }
                    jsonPath("$.transactionFee") { value(1) }
                }

        }
    }
}
//    @Test
//    fun `should return user by account number`() {
//
//        //given
//        val accountNumber = "1234"
//
//        //when
//        mockMvc.get("/user/$accountNumber")
//            .andDo { print() }
//            .andExpect {
//                status { isOk() }
//                content { contentType(MediaType.APPLICATION_JSON) }
//                jsonPath("$.trust") { value(3.14) }
//                jsonPath("$.transactionFee") { value(1) }
//            }
//
//    }
//}