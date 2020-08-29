package ru.hadron.kotlin_dogceo_mvvm_tdd.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.entity.Dog
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.remote.models.responses.DogResponse
import ru.hadron.kotlin_dogceo_mvvm_tdd.others.Resource

/**
 * Симуляция поведения реального репозитория
 */
class FakeDogRepository : DogRepository {

    private val dogs = mutableListOf<Dog>()
    private val observeAllDogs = MutableLiveData<List<Dog>>(dogs)
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observeAllDogs.postValue(dogs)
    }

    override suspend fun insertDog(dog: Dog) {
        dogs.add(dog)
        refreshLiveData()
    }

    override suspend fun deleteDog(dog: Dog) {
        dogs.remove(dog)
        refreshLiveData()
    }

    override fun observeAllDogs(): LiveData<List<Dog>> {
        return observeAllDogs
    }

    override suspend fun handleGetAllDogs(): Resource<DogResponse.Breeds> {
       return if (shouldReturnNetworkError) {
           Resource.error("error", null)
       } else {
           Resource.success(DogResponse.Breeds(mapOf()))
       }
    }
}