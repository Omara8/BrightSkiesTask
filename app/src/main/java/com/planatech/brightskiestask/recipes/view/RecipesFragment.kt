package com.planatech.brightskiestask.recipes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.planatech.brightskiestask.R
import com.planatech.brightskiestask.databinding.FragmentRecipesBinding
import com.planatech.brightskiestask.recipes.viewmodel.RecipesViewModel

class RecipesFragment : Fragment() {

    private var binding: FragmentRecipesBinding? = null
    private var recipesViewModel: RecipesViewModel? = null
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipes, container, false)
        navController = findNavController()
        loadViewModel()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.retry?.setOnClickListener {
            recipesViewModel?.loadRecipes()
        }
    }

    private fun loadViewModel() {
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
        recipesViewModel?.loadRecipes()
        observeViewModel()
    }

    private fun observeViewModel() {
        recipesViewModel?.recipes?.observe(viewLifecycleOwner, Observer {
            val recipesAdapter = RecipesAdapter(it) { recipe ->
                recipesViewModel?.setSelectedRecipe(recipe)
                navController?.navigate(R.id.action_recipesFragment_to_recipeDetailsFragment)
            }
            binding?.recipesAdapter = recipesAdapter
            binding?.recipesRecycler?.visibility = View.VISIBLE
        })
        recipesViewModel?.isLoading?.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> {
                    binding?.loading?.visibility = View.VISIBLE
                    binding?.recipesRecycler?.visibility = View.GONE
                    binding?.retry?.visibility = View.GONE
                }
                false -> {
                    binding?.loading?.visibility = View.GONE
                }
            }
        })
        recipesViewModel?.error?.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> {
                    binding?.loading?.visibility = View.GONE
                    binding?.recipesRecycler?.visibility = View.GONE
                    binding?.retry?.visibility = View.VISIBLE
                }
                false -> {
                    binding?.retry?.visibility = View.GONE
                }
            }
        })
    }

}