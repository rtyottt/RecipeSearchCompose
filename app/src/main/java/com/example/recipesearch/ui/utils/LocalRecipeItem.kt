package com.example.recipesearch.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.recipesearch.data.local.room.SavedRecipe
import com.example.recipesearch.ui.theme.RecipeColor
import com.example.recipesearch.ui.utils.nav.Screen
import com.example.recipesearch.ui.utils.nav.encodeUrl

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LocalRecipeItem(savedRecipe: SavedRecipe, navHostController: NavHostController, onIconClick:(SavedRecipe)->Unit){
    Surface(
        Modifier.padding(10.dp,10.dp,10.dp),
        color = RecipeColor,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(modifier = Modifier.padding(10.dp)){
            GlideImage(modifier = Modifier.fillMaxWidth(),model = savedRecipe.image, contentDescription = null, contentScale = ContentScale.FillWidth)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Text(modifier = Modifier.clickable {
                    navHostController.navigate(Screen.WebScreen.withArgs(savedRecipe.url.encodeUrl()))
                }, text = savedRecipe.label)
                IconButton(
                    onClick = {
                        onIconClick(savedRecipe)
                    },
                ) {
                    Icon(Icons.Outlined.Delete,contentDescription = null)
                }
            }
        }
    }
}