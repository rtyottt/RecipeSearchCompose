package com.example.recipesearch.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ SavedRecipe::class], exportSchema = false, version = 2)
@TypeConverters(IngredientConverter::class)
abstract class SearchDatabase:RoomDatabase() {
    abstract fun getDao(): SearchDao
}