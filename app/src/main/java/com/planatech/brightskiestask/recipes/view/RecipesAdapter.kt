package com.planatech.brightskiestask.recipes.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.planatech.brightskiestask.databinding.RecipeItemBinding
import com.planatech.brightskiestask.recipes.model.Recipe

class RecipesAdapter(
    private val recipes: List<Recipe>?,
    private val itemClickCallback: (recipe: Recipe) -> Unit
) : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            RecipeItemBinding.inflate(layoutInflater, parent, false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        recipes?.let { recipes ->
            holder.binding.recipe = recipes[position]
            holder.binding.executePendingBindings()
            holder.itemView.setOnClickListener {
                itemClickCallback(recipes[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return recipes?.size ?: 0
    }

    class RecipesViewHolder(val binding: RecipeItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}