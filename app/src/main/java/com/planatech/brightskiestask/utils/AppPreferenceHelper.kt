package com.planatech.brightskiestask.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AppPreferenceHelper {

    private var sharedPreferences: SharedPreferences? = null
    private var favorites: MutableList<String>? = mutableListOf()

    fun initPreferences(context: Context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    private fun getFavorites() {
        val serializedList: String? = sharedPreferences?.getString(FAVORITES, "")
        serializedList?.let {
            val gson = Gson()
            val type = object : TypeToken<MutableList<String>?>() {}.type
            favorites = gson.fromJson(it, type) ?: mutableListOf()
        }
    }

    fun checkIsFavorite(id: String): Boolean {
        getFavorites()
        favorites?.forEach {
            if (it == id)
                return true
        }
        return false
    }

    fun addFavorite(id: String?) {
        id?.let {
            favorites?.add(it)
            val gson = Gson()
            val temp = gson.toJson(favorites)
            sharedPreferences?.edit()?.putString(FAVORITES, temp)?.apply()
        }
    }

    fun removeFavorite(id: String?) {
        favorites?.remove(id)
        val gson = Gson()
        val temp = gson.toJson(favorites)
        sharedPreferences?.edit()?.putString(FAVORITES, temp)?.apply()
    }

}