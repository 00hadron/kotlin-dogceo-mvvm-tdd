package ru.hadron.kotlin_dogceo_mvvm_tdd.repositories

import androidx.lifecycle.LiveData
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.dao.DogDao
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.entity.Dog
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.remote.api.DogApi
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.remote.models.responses.DogResponse
import ru.hadron.kotlin_dogceo_mvvm_tdd.others.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val dogDao: DogDao,
    private val dogApi: DogApi
) : DogRepository {

    override suspend fun insertDog(dog: Dog) = dogDao.insertDog(dog)
    override suspend fun deleteDog(dog: Dog)  = dogDao.deleteDog(dog)

    override fun observeAllDogs(): LiveData<List<Dog>> = dogDao.observeAllDogs()

    //-----------------

    override suspend fun handleGetAllDogs(): Resource<DogResponse.Breeds> {
        return try {
            val response = dogApi.getAllDogs()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("unknown error.", null)
            } else {
                Resource.error("unknown error.", null)
            }
        } catch (exp: Exception) {
            Resource.error("check you internet connection.", null)
        }
    }
}