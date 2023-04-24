package com.aminovic.obs.di

import android.app.Application
import androidx.room.Room
import com.aminovic.obs.data.local.ObsDao
import com.aminovic.obs.data.local.ObsDatabase
import com.aminovic.obs.data.remote.ObsApi
import com.aminovic.obs.data.remote.ObsApi.Companion.BASE_URL
import com.aminovic.obs.data.repository.ObsRepositoryImpl
import com.aminovic.obs.domain.repository.ObsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(app: Application): OkHttpClient {
        val cacheSize = (12 * 1024 * 1024).toLong() // 5 MB
        val cacheDirectory = File(app.cacheDir, "http-cache")
        val cache = Cache(cacheDirectory, cacheSize)

        return OkHttpClient.Builder()
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideObsApi(okHttpClient: OkHttpClient): ObsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideObsDatabase(app: Application): ObsDao {
        return Room.databaseBuilder(
            app,
            ObsDatabase::class.java,
            "obs_database"
        ).build().dao
    }

    @Provides
    @Singleton
    fun provideObsRepository(api: ObsApi, database: ObsDao): ObsRepository {
        return ObsRepositoryImpl(api = api, dao = database)
    }
}