package com.example.dinosaurs.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType

private const val BASE_URL =
    "https://kareemy.github.io"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface DinoApiService {
    @GET("dinosaurs.json")
    suspend fun getPhotos(): List<DinoPhoto>
}
object DinoApi {
    val retrofitService : DinoApiService by lazy {
        retrofit.create(DinoApiService::class.java)
    }
}