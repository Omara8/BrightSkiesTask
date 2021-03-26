package com.planatech.brightskiestask.recipes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.planatech.brightskiestask.recipes.model.Recipe
import com.planatech.brightskiestask.recipes.repository.RecipesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {

    private val recipesRepository = RecipesRepository()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private var _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private var _selectedRecipe = MutableLiveData<Recipe>()
    val selectedRecipe: LiveData<Recipe> = _selectedRecipe

    fun loadRecipes() {
        viewModelScope.launch {
            recipesRepository.loadRecipes()
                .onStart {
                    _isLoading.postValue(true)
                    _error.postValue(false)
                }
                .catch {
                    _error.postValue(true)
                    _isLoading.postValue(false)
                }
                .collect {
                    _isLoading.postValue(false)
                    _recipes.postValue(it)
                }
        }
    }

    fun setSelectedRecipe(recipe: Recipe){
        _selectedRecipe.postValue(recipe)
    }

}