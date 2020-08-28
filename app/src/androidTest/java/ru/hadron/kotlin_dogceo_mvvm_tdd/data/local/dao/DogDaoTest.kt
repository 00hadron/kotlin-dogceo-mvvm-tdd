package ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.dao

/**
 * Тестирование Room Database.
 * Room require context ---> in android test
 * LiveDataUilAndroidTest.kt ----> getOrAwaitValue() (transform LiveData in List)
 * @ExperimentalCoroutinesApi ---> runBlockTest {...}
 */

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.db.DogsDatabase
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.entity.Dog
import ru.hadron.kotlin_dogceo_mvvm_tdd.getOrAwaitValue

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DogDaoTest {

    private lateinit var database: DogsDatabase
    private lateinit var dao: DogDao

    /**
     * Behind the scenes, this bypasses the main thread check, and immediately runs any tasks on your test thread.
     * Исправляет ошибку java.lang.IllegalStateException: This job has not completed yet (из-за LiveData).
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * Init db in RAM, dao.
     * Выполнять в main потоке.
     */
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DogsDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.getDogDao()
    }

    /**
     * Закрыть db после выполнения всех тестов.
     */
    @After
    fun teardown() = database.close()


    @Test
    fun insertDogTest() = runBlockingTest {
        val dog = Dog("test_dog", id = 1)
        dao.insertDog(dog = dog)
        val allDogs = dao.observeAllDogs().getOrAwaitValue()
        assertThat(allDogs).contains(dog)
    }


    @Test
    fun deleteDogTest() = runBlockingTest {
        val dog = Dog("test_dog1")
        dao.insertDog(dog = dog)
        dao.deleteDog(dog = dog)
        val allDogs = dao.observeAllDogs().getOrAwaitValue()
        assertThat(allDogs).doesNotContain(dog)
    }
}