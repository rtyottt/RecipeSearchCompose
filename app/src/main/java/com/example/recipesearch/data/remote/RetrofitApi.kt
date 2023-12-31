package com.example.recipesearch.data.remote

import com.example.recipesearch.RecipeResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("api/recipes/v2?type=public&app_id=39950132&app_key=e9a0eae23394d3dcfd0ec951c62622fb")
    suspend fun getRecipesBySearch(@Query("q") q: String):Response<RecipeResult>
}