package ru.hadron.kotlin_dogceo_mvvm_tdd.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.remote.models.responses.DogResponse

interface DogApi {
    @GET("api/breeds/list/all")
    suspend fun getAllDogs(): Response<DogResponse.Breeds>
}