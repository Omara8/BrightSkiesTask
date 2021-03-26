package com.planatech.brightskiestask.recipes.repository

import com.planatech.brightskiestask.recipes.model.Recipe
import retrofit2.http.GET

interface RecipesAPI {

    @GET("43427003d33f1f6b51cc")
    suspend fun loadRecipes(): List<Recipe>
}