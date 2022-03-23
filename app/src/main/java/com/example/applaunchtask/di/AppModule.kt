package com.example.applaunchtask.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.applaunchtask.data.local.MainDao
import com.example.applaunchtask.data.local.MainDatabase
import com.example.applaunchtask.data.remote.WeatherApi
import com.example.applaunchtask.data.remote.WeatherApi.Companion.BASE_URL
import com.example.applaunchtask.data.repository.MainRepositoryImpl
import com.example.applaunchtask.domain.repository.MainRepository
import com.example.applaunchtask.util.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // retrofit
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)

    // session manager
    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context) =
        SessionManager(context)

    @Provides
    @Singleton
    fun provideMainDatabase(app: Application): MainDatabase =
        Room.databaseBuilder(app, MainDatabase::class.java, "mainDatabase").build()

    @Provides
    @Singleton
    fun provideMainDao(db: MainDatabase) = db.mainDao

    @Provides
    @Singleton
    fun provideMainRepository(
        api: WeatherApi,
        db: MainDao): MainRepository {
        return MainRepositoryImpl(api, db)
    }
}