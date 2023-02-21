package com.shaka.newproject.datasource

import com.shaka.newproject.model.Users

interface UserDataSource {

    fun retriveUser(): Collection<Users>
    fun workUser(accountNumber: String): Users


}