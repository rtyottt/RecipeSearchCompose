package com.example.recipesearch.ui.screens.save

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesearch.data.local.room.SavedRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    var repository: SaveRepository
    ):ViewModel() {
    var _deletedRecipe = Channel<SavedRecipe>(Channel.CONFLATED)
    var deletedRecipe = _deletedRecipe.receiveAsFlow()
    val searchQuery = MutableStateFlow("")
    var savedRecipe: Flow<List<SavedRecipe>> = searchQuery.flatMapLatest {
        repository.getSavedRecipe(it)
    }
    fun updateDeletedRecipe(savedRecipe: SavedRecipe){
        viewModelScope.launch {
           _deletedRecipe.send(savedRecipe)
        }
    }
    fun updateQuery(query:String){
        searchQuery.value = query
    }
    fun deleteRecipe(recipe: SavedRecipe){
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }
    }
    fun undoDelete(savedRecipe: SavedRecipe){
        viewModelScope.launch {
            repository.unDeleteRecipe(savedRecipe)
        }
    }
}
