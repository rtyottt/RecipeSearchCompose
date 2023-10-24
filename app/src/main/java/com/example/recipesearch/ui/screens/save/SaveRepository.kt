package com.example.recipesearch.ui.screens.save

import com.example.recipesearch.data.local.room.SavedRecipe
import com.example.recipesearch.data.local.room.SearchDao

class SaveRepository(var searchDao: SearchDao) {
    suspend fun deleteRecipe(recipe: SavedRecipe){
        searchDao.deleteRecipe(recipe)
    }
    suspend fun unDeleteRecipe(recipe: SavedRecipe){
        searchDao.saveRecipe(recipe)
    }
    fun getSavedRecipe(searchQuery:String) = searchDao.getSavedRecipe(searchQuery)
}