package ru.hadron.kotlin_dogceo_mvvm_tdd.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.local.db.DogsDatabase
import ru.hadron.kotlin_dogceo_mvvm_tdd.data.remote.api.DogApi
import ru.hadron.kotlin_dogceo_mvvm_tdd.others.Constants.BASE_URL
import ru.hadron.kotlin_dogceo_mvvm_tdd.others.Constants.DOGS_DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDogsDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        DogsDatabase::class.java,
        DOGS_DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideDogDao(db: DogsDatabase) = db.getDogDao()

    @Provides
    @Singleton
    fun provideDogApi(): DogApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(DogApi::class.java)
    }
}