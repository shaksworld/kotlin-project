package com.shaka.newproject.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.shaka.newproject.model.Users
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
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class UserControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
        ) {

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
                    jsonPath("$[0].name") { value("shaka") }
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
            val name = "shaka"

            //when
            val andExpect = mockMvc.get("/user/$name")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
//                    jsonPath("$.name") { value(name) }
                    jsonPath("$.phoneNumber") { value(123456789) }
                    jsonPath("$.address") { value("Nigeria") }
                }

        }

        @Test
        fun `should return 404 when user not found`() {

            //given
            val name = "does_not_exist"

            //when
            mockMvc.get("/user/$name")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

        }

        @Nested
        @DisplayName("POST /create")
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        inner class CreateUser {

            @Test
            fun `should create new user`() {

                //given
                val newUsers = Users("muhammed", 123456789, "Nigeria")

                // when
                val performPost = mockMvc.post("/user/create") {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(newUsers)
                }
                //then
                performPost
                    .andDo { print() }
                    .andExpect {
                        status { isCreated() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$.name") { value(newUsers.name) }
                        jsonPath("$.phoneNumber") { value(newUsers.phoneNumber) }
                        jsonPath("$.address") { value(newUsers.address) }
                    }
            }

            //
//            mockMvc.get("/user/${newUsers.name}")
//                .andExpect {
//                    status { isOk() }
//                    content { contentType(MediaType.APPLICATION_JSON) }
//                    jsonPath("$.name") { value(newUsers.name) }
//                    jsonPath("$.phoneNumber") { value(newUsers.phoneNumber) }
//                    jsonPath("$.address") { value(newUsers.address) }
//                }
            @Nested
            @DisplayName("PATCH /user")
            @TestInstance(TestInstance.Lifecycle.PER_CLASS)
            inner class PatchExistingUser {

                @Test
                fun `should update and exiting user`() {
                    //given
                    val nameUpdate = Users("shaka", 123456789, "USA")

                    //when
                    val performPatch = mockMvc.patch("/user") {
                        contentType = MediaType.APPLICATION_JSON
                        content = objectMapper.writeValueAsString(nameUpdate)
                    }

                    //then
                    performPatch
                        .andDo { print() }
                        .andExpect {
                            status { isOk() }
                            content { contentType(MediaType.APPLICATION_JSON) }
                            jsonPath("$.name") { value(nameUpdate.name) }
                            jsonPath("$.phoneNumber") { value(nameUpdate.phoneNumber) }
                            jsonPath("$.address") { value(nameUpdate.address) }
                        }
                }

                @Test
                fun `should return BAD REQUEST if no user exist`() {
                    //given
                    val nameUpdate = Users("does_not_exist", 123409, "USA")

                    //when
                    val performPatch = mockMvc.patch("/user") {
                        contentType = MediaType.APPLICATION_JSON
                        content = objectMapper.writeValueAsString(nameUpdate)
                    }

                    //then
                    performPatch
                        .andDo { print() }
                        .andExpect {
                            status { isNotFound( ) }
                        }
                }

                @Nested
                @DisplayName("DELETE /user/{name}")
                @TestInstance(TestInstance.Lifecycle.PER_CLASS)
                inner class DeleteUser {

                    @Test
                    fun `should delete user`() {
                        //given
                        val name = "shaka"

                        //when
                        val performDelete = mockMvc.delete("/user/$name")

                        //then
                        performDelete
                            .andDo { print() }
                            .andExpect {
                                status { isNoContent() }
                            }
                        mockMvc.get("/user/$name")
                            .andExpect {
                                status { isNotFound() }
                            }
                    }

                    @Test
                    fun `should return BAD REQUEST if no user exist`() {
                        //given
                        val name = "does_not_exist"

                        //when
                        val performDelete = mockMvc.delete("/user/$name")

                        //then
                        performDelete
                            .andDo { print() }
                            .andExpect {
                                status { isNotFound() }
                            }
                    }
                }

//            @Test
//            fun `should return BAD REQUEST when name already exist` (){
//                val invalidUser = Users("taiwo", 987656789, "Tiawan")
//
//                val performPost =mockMvc.post("/user/create") {
//                    contentType = MediaType.APPLICATION_JSON
//                    content = objectMapper.writeValueAsString(invalidUser)
//                }
//                performPost
//                    .andDo { print() }
//                    .andExpect {
//                        status { isBadRequest() }
//                    }
//            }
            }
        }
    }
}


