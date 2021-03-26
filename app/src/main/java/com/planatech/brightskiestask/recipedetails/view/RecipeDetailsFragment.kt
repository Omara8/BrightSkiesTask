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
import com.planatech.brightskiestask.recipes.model.Recipe
import com.planatech.brightskiestask.recipes.viewmodel.RecipesViewModel
import com.planatech.brightskiestask.utils.AppPreferenceHelper
import kotlinx.android.synthetic.main.fragment_recipe_details.*

class RecipeDetailsFragment : Fragment() {

    private var binding: FragmentRecipeDetailsBinding? = null
    private var recipesViewModel: RecipesViewModel? = null
    private var selectedRecipe: Recipe? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_details, container, false)
        loadViewModel()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favorite?.setOnClickListener {
            it.isSelected = !it.isSelected
            if (it.isSelected)
                AppPreferenceHelper.addFavorite(selectedRecipe?.id)
            else
                AppPreferenceHelper.removeFavorite(selectedRecipe?.id)
        }
    }

    private fun loadViewModel() {
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        recipesViewModel?.selectedRecipe?.observe(viewLifecycleOwner, Observer {
            selectedRecipe = it
            binding?.recipe = it
            favorite?.isSelected = selectedRecipe?.id?.let {
                AppPreferenceHelper.checkIsFavorite(it)
            } ?: false
        })
    }

}