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
import org.springframework.test.web.servlet.post

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
                    jsonPath("$[0].name") { value("Shaka") }
                }
        }
    }

    @Nested
    @DisplayName("GET /user/{name}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetUserByAccountNumber {

        @Test
        fun `should return user by name`() {

            //given
            val name = "Shaka"

            //when
            mockMvc.get("/user/$name")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.name") { value(name) }
                    jsonPath("$.phoneNumber") { value(1234) }
                    jsonPath("$.address") { value("kigali") }
                }

        }

//        @Test
//        fun `should return user by `() {
//
//            //given
//            val name = "Shaka"
//
//            //when
//            mockMvc.get("/user/$name")
//                .andDo { print() }
//                .andExpect {
//                    status { isOk() }
//                    content { contentType(MediaType.APPLICATION_JSON) }
//                    jsonPath("$.name") { value(name) }
////                    jsonPath("$.trust") { value(3.14) }
////                    jsonPath("$.transactionFee") { value(1) }
//                }
//
//        }
//
//        @Test
//        fun `should return 404 when user not found`() {
//
//            //given
//            val name = "Shaka"
//
//            //when
//            mockMvc.get("/user/$name")
//                .andDo { print() }
//                .andExpect {
//                    status { isNotFound() }
//                }
//
//        }

        @Nested
        @DisplayName("POST /create")
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        inner class CreateUser {

            @Test
            fun `should create new user`() {

                //given
                val name = "Shaka"
                val phoneNumber = 1234
                val address = "Kigali"

                //when
                mockMvc.post("/user/create") {
                    contentType = MediaType.APPLICATION_JSON
                    content = """
                        {
                            "name": "$name",
                            "phoneNumber": $phoneNumber,
                            "address": "$address"
                        }
                    """.trimIndent()
                }
                    .andDo { print() }
                    .andExpect {
                        status { isCreated() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$.name") { value(name) }
                        jsonPath("$.phoneNumber") { value(phoneNumber) }
                        jsonPath("$.address") { value(address) }
//                        jsonPath("$.accountNumber") { value(accountNumber) }
//                        jsonPath("$.trust") { value(trust) }
//                        jsonPath("$.transactionFee") { value(transactionFee) }
                    }
            }

            @Nested
            @DisplayName("POST /create")
            @TestInstance(TestInstance.Lifecycle.PER_CLASS)
            inner class UpdateUser {
                @Test
                fun `should create new user`() {

                    //given
                    val name = "Shaka"
                    val phoneNumber = 1234
                    val address = "Kigali"

                    //when
                    mockMvc.post("/user/update") {
                        contentType = MediaType.APPLICATION_JSON
                        content = """
                        {
                            "name": "$name",
                            "phoneNumber": $phoneNumber,
                            "address": "$address"
                        }
                    """.trimIndent()
                    }
                        .andDo { print() }
                        .andExpect {
                            status { isCreated() }
                            content { contentType(MediaType.APPLICATION_JSON) }
                            jsonPath("$.name") { value(name) }
                            jsonPath("$.phoneNumber") { value(phoneNumber) }
                            jsonPath("$.address") { value(address) }

                        }
                }
            }
        }
    }
}


