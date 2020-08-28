package ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.dao.DogDao
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.entity.Dog

@Database(
    entities = [Dog::class],
    version = 1
)
abstract class DogsDatabase : RoomDatabase() {

    abstract fun getDogDao(): DogDao
}