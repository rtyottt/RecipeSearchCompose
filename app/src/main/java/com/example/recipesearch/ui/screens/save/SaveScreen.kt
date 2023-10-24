package com.example.recipesearch.ui.screens.save

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.recipesearch.data.local.room.SavedRecipe
import com.example.recipesearch.ui.utils.LocalRecipeItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SaveScreen(navHostController: NavHostController, viewModel: SaveViewModel = hiltViewModel()){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(topBar = {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text ,
            onValueChange = {
                text = it
                viewModel.updateQuery(text.text)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { viewModel.updateQuery(text.text) },),
            placeholder = {
                Text("Search saved recipe...")
            },
        )
    },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }){ paddingValues->
        val Recipe = viewModel.savedRecipe.collectAsStateWithLifecycle(
            initialValue = listOf<SavedRecipe>()
        )
        val onClickDelete: (SavedRecipe)-> Unit = { deletedRecipe->
            viewModel.deleteRecipe(deletedRecipe)
            viewModel.updateDeletedRecipe(deletedRecipe)
            scope.launch {
                val result = snackbarHostState
                    .showSnackbar(
                        message = "${deletedRecipe.label} was deleted",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Long
                    )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.undoDelete(deletedRecipe)
                    }
                    SnackbarResult.Dismissed -> {
                    }
                }
            }
        }
        LazyColumn(
            Modifier.fillMaxSize().padding(paddingValues)
        ) {
            items(Recipe.value){item->
                LocalRecipeItem(savedRecipe = item, navHostController = navHostController, onIconClick = {onClickDelete(item)})
            }
        }
    }
}