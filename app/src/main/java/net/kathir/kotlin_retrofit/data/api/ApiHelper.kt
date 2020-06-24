package net.kathir.kotlin_retrofit.data.api

class ApiHelper(private val apiService: ApiService)
{
    suspend fun getUsers() = apiService.getUsers();
}
