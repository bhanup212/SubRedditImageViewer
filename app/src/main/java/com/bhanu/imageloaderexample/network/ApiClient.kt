package com.bhanu.imageloaderexample.network

import com.bhanu.imageloaderexample.model.RedditImages
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import java.util.concurrent.TimeUnit


/**
 * Created by Bhanu Prakash Pasupula on 03,Jun-2020.
 * Email: pasupula1995@gmail.com
 */
interface ApiClient {
    @GET("/r/images/hot.json")
    suspend fun getSubRedditImages(): RedditImages


    companion object {
        private val okHttpAuthClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .build()

        val apiClient = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpAuthClient)
            .build()
            .create(ApiClient::class.java)
    }

}