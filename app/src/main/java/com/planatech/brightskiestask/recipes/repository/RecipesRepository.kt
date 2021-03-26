package com.planatech.brightskiestask.recipes.repository

import com.planatech.brightskiestask.recipes.model.Recipe
import com.planatech.brightskiestask.utils.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RecipesRepository {

    private val retrofit = RetrofitBuilder.getRetrofit()
    private val client = retrofit.create(RecipesAPI::class.java)

    fun loadRecipes(): Flow<List<Recipe>> {
        return flow {
            val recipes = client.loadRecipes()
            emit(recipes)
        }.flowOn(Dispatchers.IO)
    }
}