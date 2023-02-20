package com.shaka.newproject.datasource

import com.shaka.newproject.model.Users

interface UserDataSource {

    fun getUser(): Collection<Users>
}