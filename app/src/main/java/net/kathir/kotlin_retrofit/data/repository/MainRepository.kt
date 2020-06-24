package net.kathir.kotlin_retrofit.data.repository

import net.kathir.kotlin_retrofit.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}