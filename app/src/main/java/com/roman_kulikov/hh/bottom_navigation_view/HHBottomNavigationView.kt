package com.roman_kulikov.hh.bottom_navigation_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.roman_kulikov.hh.R
import com.roman_kulikov.hh.databinding.ViewBottomNavigationBinding

class HHBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attr, defStyleAttr) {
    private lateinit var listener: OnItemClickListener

    private val binding: ViewBottomNavigationBinding by lazy { ViewBottomNavigationBinding.inflate(LayoutInflater.from(context), this, true) }


    init {
        inflate(context, R.layout.view_bottom_navigation, this)
        initListeners()
    }

    fun setHandler(listener: OnItemClickListener) {
        this.listener = listener
    }

    private fun initListeners() {
        binding.layoutSearch.setOnClickListener {
            selectItem(BottomNavigationItem.SEARCH, binding.layoutSearch)
        }
        binding.layoutFavorite.setOnClickListener {
            selectItem(BottomNavigationItem.FAVORITES, binding.layoutFavorite)
        }
        binding.layoutResponse.setOnClickListener {
            selectItem(BottomNavigationItem.RESPONSE, binding.layoutResponse)
        }
        binding.layoutMessages.setOnClickListener {
            selectItem(BottomNavigationItem.MESSAGES, binding.layoutMessages)
        }
        binding.layoutProfile.setOnClickListener {
            selectItem(BottomNavigationItem.PROFILE, binding.layoutProfile)
        }
    }

    private fun selectItem(item: BottomNavigationItem, itemView: View) {
        listener.onSelectItem(item)
        resetAllItems()
        itemView.isSelected = true
    }

    private fun resetAllItems() {
        binding.layoutSearch.isSelected = false
        binding.layoutFavorite.isSelected = false
        binding.layoutResponse.isSelected = false
        binding.layoutMessages.isSelected = false
        binding.layoutProfile.isSelected = false
    }

    fun setFavoriteVacancyNumber(number: Int) {
        if (number > 0) {
            binding.layoutBadgeFavorite.visibility = View.VISIBLE
            binding.titleFavoriteNumber.text = StringBuilder(number.toString())
        } else {
            binding.layoutBadgeFavorite.visibility = View.GONE
        }
    }


    interface OnItemClickListener {
        fun onSelectItem(item: BottomNavigationItem)
    }
}