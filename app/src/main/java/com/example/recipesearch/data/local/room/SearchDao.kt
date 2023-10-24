package com.example.recipesearch.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    @Query("SELECT * FROM saved_table WHERE label LIKE '%' || :query || '%'")
    fun getSavedRecipe(query:String):Flow<List<SavedRecipe>>
    @Delete
    suspend fun deleteRecipe(savedRecipe: SavedRecipe)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipe(savedRecipe: SavedRecipe)
}