package com.example.recipesearch.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.recipesearch.data.local.datastore.RecipeKeys
import com.example.recipesearch.data.local.room.SearchDao
import com.example.recipesearch.data.local.room.SearchDatabase
import com.example.recipesearch.data.remote.RetrofitApi
import com.example.recipesearch.ui.screens.save.SaveRepository
import com.example.recipesearch.ui.screens.search.SearchRepository
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
object RecipeModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(getOkHttpInstance:OkHttpClient) = Retrofit.Builder()
            .client(getOkHttpInstance)
            .baseUrl("https://api.edamam.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    @Provides
    @Singleton
    fun provideDatabase(context: Application) =
        Room.databaseBuilder(context, SearchDatabase::class.java,"search_database")
        .fallbackToDestructiveMigration()
        .build()
    @Provides
    fun provideDao(database: SearchDatabase) = database.getDao()

    @Provides
    @Singleton
    fun providePrefDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(RecipeKeys.recipeKey)
            }
        )
    @Provides
    fun provideSearchRepository(searchDao: SearchDao, prefs: DataStore<Preferences>, retrofit: RetrofitApi) = SearchRepository(searchDao,prefs,retrofit)
    @Provides
    fun provideSaveRepository(searchDao: SearchDao) = SaveRepository(searchDao)
}