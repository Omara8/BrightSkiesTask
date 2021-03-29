package com.planatech.brightskiestask

import com.planatech.brightskiestask.utils.AppPreferenceHelper.addFavorite
import com.planatech.brightskiestask.utils.AppPreferenceHelper.checkIsFavorite
import com.planatech.brightskiestask.utils.AppPreferenceHelper.getFavorites
import com.planatech.brightskiestask.utils.AppPreferenceHelper.removeFavorite
import org.junit.Assert.assertEquals
import org.junit.Test

class AppPreferenceHelperTest {

    @Test
    fun testGetFavorites(){
        val favorites = mutableListOf("firstId", "randomId", "thirdFavorite", "cookiesId")
        favorites.forEach {
            addFavorite(it)
        }
        val results = getFavorites()
        assertEquals(favorites, results)
    }

    @Test
    fun testCheckIsFavorite(){
        addFavorite("testId")
        val result = checkIsFavorite("testId")
        assertEquals(true, result)
    }

    @Test
    fun testCheckIsNotFavorite(){
        addFavorite("testId")
        val result = checkIsFavorite("randomId")
        assertEquals(false, result)
    }

    @Test
    fun testRemoveFavorite(){
        addFavorite("testId")
        val addResult = checkIsFavorite("testId")
        assertEquals(true, addResult)
        removeFavorite("testId")
        val removeResult = checkIsFavorite("testId")
        assertEquals(false, removeResult)
    }

}