package ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.entity.Dog

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: Dog)

    @Delete
    suspend fun deleteDog(dog: Dog)

    @Query("SELECT * FROM dogs_table")
    fun observeAllDogs(): LiveData<List<Dog>>
}