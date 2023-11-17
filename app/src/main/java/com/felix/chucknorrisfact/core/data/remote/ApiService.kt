package com.felix.chucknorrisfact.core.data.remote

import com.felix.chucknorrisfact.core.data.remote.response.FactResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("random")
    suspend fun getRandomFact(
        @Query("category") category: String? = null
    ): FactResponse

    @GET("categories")
    suspend fun getCategories(): List<String>
}