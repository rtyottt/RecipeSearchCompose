package com.example.recipesearch.data.local.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("latest_recipe")

class DataStoreManager @Inject constructor(applicationContext: Application) {
    val recipeDataStore = applicationContext.applicationContext.dataStore

}