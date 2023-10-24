package com.example.recipesearch.data.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object RecipeKeys {
    val datastoreKey = stringPreferencesKey("latest_recipe")
    const val recipeKey = "latest_recipe"
    const val defaultRecipe = "salad"
}