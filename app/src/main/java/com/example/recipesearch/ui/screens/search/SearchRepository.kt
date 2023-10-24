package com.example.recipesearch.ui.screens.search

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.recipesearch.Hit
import com.example.recipesearch.data.local.datastore.RecipeKeys
import com.example.recipesearch.data.local.datastore.RecipeKeys.datastoreKey
import com.example.recipesearch.data.remote.RetrofitApi
import com.example.recipesearch.data.local.room.SavedRecipe
import com.example.recipesearch.data.local.room.SearchDao
import kotlinx.coroutines.flow.map

class SearchRepository (
    var searchDao: SearchDao,
    val dataStore: DataStore<Preferences>,
    var retrofit: RetrofitApi
) {
    suspend fun updatePrefs(query:String){
        dataStore.edit { latestRecipe->
            latestRecipe[datastoreKey] = query
        }
    }
    fun prefsGetLastQuery() = dataStore.data.map { latestRecipe->
            latestRecipe[datastoreKey] ?: RecipeKeys.defaultRecipe
    }

    suspend fun saveRecipe(hit: Hit){
        searchDao.saveRecipe(SavedRecipe(ingredients = hit.recipe.ingredients, label = hit.recipe.label, image = hit.recipe.image, calories = hit.recipe.calories, url = hit.recipe.url))
    }

    suspend fun getRecipeBySearch(query: String) = retrofit.getRecipesBySearch(query).body()?.hits
}