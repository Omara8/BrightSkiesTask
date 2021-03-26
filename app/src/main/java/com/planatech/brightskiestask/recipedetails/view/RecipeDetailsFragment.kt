package com.planatech.brightskiestask.recipedetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.planatech.brightskiestask.R
import com.planatech.brightskiestask.databinding.FragmentRecipeDetailsBinding
import com.planatech.brightskiestask.recipes.viewmodel.RecipesViewModel

class RecipeDetailsFragment: Fragment() {

    private var binding: FragmentRecipeDetailsBinding? = null
    private var recipesViewModel: RecipesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_details, container, false)
        loadViewModel()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun loadViewModel(){
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel(){
        recipesViewModel?.selectedRecipe?.observe(viewLifecycleOwner, Observer {
            binding?.recipe = it
        })
    }

}