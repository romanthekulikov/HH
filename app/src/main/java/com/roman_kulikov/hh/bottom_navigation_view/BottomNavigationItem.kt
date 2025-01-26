package com.roman_kulikov.hh.bottom_navigation_view

import com.roman_kulikov.hh.R

enum class BottomNavigationItem(val id: Int) {
    SEARCH(R.id.layout_search),
    FAVORITES(R.id.layout_favorite),
    RESPONSE(R.id.layout_response),
    MESSAGES(R.id.layout_messages),
    PROFILE(R.id.layout_profile)
}