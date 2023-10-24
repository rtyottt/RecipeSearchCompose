package com.example.recipesearch.data.local.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.recipesearch.Ingredient

@Entity(tableName = "saved_table",
    indices = [Index(value = ["label"], unique = true)])
data class SavedRecipe(
    val ingredients: List<Ingredient>,
    val label: String,
    val image: String,
    val calories: Double,
    val url: String
){
    @PrimaryKey(autoGenerate = true)
    var localId: Int = 0
}

