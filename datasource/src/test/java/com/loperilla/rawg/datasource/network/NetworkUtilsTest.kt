package com.loperilla.rawg.datasource.network

import com.loperilla.rawg.datasource.network.NetworkUtils.getPageValue
import junit.framework.Assert.assertEquals
import org.junit.Test

class NetworkUtilsTest {
    @Test
    fun testPageValuePresent() {
        val urlString = "https://api.rawg.io/api/games?key=d281bc65531746df962f36c5098785b5&page=2&search=fall-guys&search_exact=true"
        assertEquals(2, urlString.getPageValue())
    }

    @Test
    fun testPageTwoNumberValuePresent() {
        val urlString = "https://api.rawg.io/api/games?key=d281bc65531746df962f36c5098785b5&page=12&search=fall-guys&search_exact=true"
        assertEquals(12, urlString.getPageValue())
    }

    @Test
    fun testPageValueNotPresent() {
        val urlString = "https://api.rawg.io/api/games?key=d281bc65531746df962f36c5098785b5&search=fall-guys&search_exact=true"
        assertEquals(null, urlString.getPageValue())
    }

}