package ru.hadron.kotlin_dogceo_mvvm_tdd.repositories

import androidx.lifecycle.LiveData
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.entity.Dog
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.remote.models.responses.DogResponse
import ru.hadron.kotlin_dogceo_mvvm_tdd.others.Resource

interface DogRepository {

    suspend fun insertDog(dog: Dog)
    suspend fun deleteDog(dog: Dog)

    fun observeAllDogs(): LiveData<List<Dog>>

    //-------

    suspend fun handleGetAllDogs(): Resource<DogResponse.Breeds>
}
