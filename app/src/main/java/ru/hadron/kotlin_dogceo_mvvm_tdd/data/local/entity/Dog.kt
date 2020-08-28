package ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs_table")
data class Dog(
    val breed: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}