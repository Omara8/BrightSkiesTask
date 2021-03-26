package com.planatech.brightskiestask.recipes.model

data class Recipe(
    val id : String,
    val fats : String,
    val name : String,
    val time : String,
    val image : String,
    val weeks : List<String>,
    val carbos : String,
    val fibers : String,
    val rating : String,
    val country : String,
    val ratings : String,
    val calories : String,
    val headline : String,
    val keywordswords : List<String>,
    val products : List<String>,
    val proteins : String,
    val favorites : Int,
    val difficulty : Int,
    val description : String,
    val highlighted : Boolean,
    val ingredients : List<String>,
    val incompatibilities : String,
    val deliverable_ingredients : List<String>,
    val undeliverable_ingredients : List<String>
)