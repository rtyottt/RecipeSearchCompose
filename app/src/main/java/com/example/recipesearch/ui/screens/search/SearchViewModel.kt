package com.example.recipesearch.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesearch.Hit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    var repository: SearchRepository
):ViewModel() {

    private var _searchResult = MutableStateFlow<List<Hit>>(emptyList())
    var searchResult: SharedFlow<List<Hit>> = _searchResult.asStateFlow()
    init {
        viewModelScope.launch {
            repository.prefsGetLastQuery().collectLatest { ass ->
                getData(ass)
            }
        }
    }
    fun getData(query: String){
        viewModelScope.launch {
            repository.getRecipeBySearch(query)?.let { _searchResult.emit(it) }
        }
    }
    fun updateQuery(query: String){
        viewModelScope.launch {
            repository.updatePrefs(query)
        }
    }
    fun saveRecipe(hit: Hit){
        viewModelScope.launch {
            repository.saveRecipe(hit)
        }
    }
    fun anusss(){
//        viewModelScope.launch {
//            repository.getRecipeBySearch("salad").let { it?.hits?.let { it1 ->
//                _searchResult.emit(
//                    it1
//                )
//            } }
//        }
    }
}