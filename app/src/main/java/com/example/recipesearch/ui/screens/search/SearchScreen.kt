package com.example.recipesearch.ui.screens.search

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.recipesearch.Hit
import com.example.recipesearch.ui.utils.RemoteRecipeItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun SearchScreen(navHostController: NavHostController, viewModel: SearchViewModel = hiltViewModel()) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(topBar = {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text ,
            onValueChange = { text = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { viewModel.updateQuery(text.text) },
            ),
            placeholder = {
                Text("Search new recipe...")
            },
        )
    }) { contentPadding  ->
        val state = viewModel.searchResult.collectAsStateWithLifecycle(
            initialValue = listOf<Hit>()
        )
        val context = LocalContext.current
        LazyColumn(
            Modifier.fillMaxSize().padding(contentPadding)
        ) {
            items(state.value){item->
                RemoteRecipeItem(recipe = item.recipe, navHostController = navHostController, onIconClick = {
                    viewModel.saveRecipe(item)
                    Toast.makeText(context, "${it.label} was added", Toast.LENGTH_LONG).show()}
                )
            }
        }
    }
}
