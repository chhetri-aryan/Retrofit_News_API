package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v1/news")
    fun getTopHeadlines(
        @Query("access_key") apiKey: String,
        @Query("countries") country: String = "us",
        @Query("limit") limit: Int = 5,
    ): Call<NewsResponse>
}
