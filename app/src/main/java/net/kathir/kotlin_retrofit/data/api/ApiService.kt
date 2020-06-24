package net.kathir.kotlin_retrofit.data.api

import net.kathir.kotlin_retrofit.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}