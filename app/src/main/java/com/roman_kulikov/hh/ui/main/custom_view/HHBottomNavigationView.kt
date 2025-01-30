package com.roman_kulikov.hh.ui.main.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.roman_kulikov.hh.databinding.ViewBottomNavigationBinding

class HHBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attr, defStyleAttr) {
    private lateinit var listener: OnItemClickListener

    private val binding: ViewBottomNavigationBinding by lazy { ViewBottomNavigationBinding.inflate(LayoutInflater.from(context), this, true) }
    private var currentItem = Item(binding.imageSearch, binding.titleSearch)

    init {
        currentItem.iconView.isSelected = true
        currentItem.textView.isSelected = true
        initListeners()
    }

    fun setHandler(listener: OnItemClickListener) {
        this.listener = listener
    }

    private fun initListeners() {
        binding.layoutSearch.setOnClickListener {
            selectItem(BottomNavigationItem.SEARCH, Item(binding.imageSearch, binding.titleSearch))
        }
        binding.layoutFavorite.setOnClickListener {
            selectItem(BottomNavigationItem.FAVORITES, Item(binding.imageFavorite, binding.titleFavorite))
        }
        binding.layoutResponse.setOnClickListener {
            selectItem(BottomNavigationItem.RESPONSE, Item(binding.imageResponse, binding.titleResponse))
        }
        binding.layoutMessages.setOnClickListener {
            selectItem(BottomNavigationItem.MESSAGES, Item(binding.imageMessages, binding.titleMessages))
        }
        binding.layoutProfile.setOnClickListener {
            selectItem(BottomNavigationItem.PROFILE, Item(binding.imageProfile, binding.titleProfile))
        }
    }

    private fun selectItem(item: BottomNavigationItem, viewItem: Item) {
        listener.onSelectItem(item)
        currentItem.iconView.isSelected = false
        currentItem.textView.isSelected = false
        currentItem = viewItem
        currentItem.iconView.isSelected = true
        currentItem.textView.isSelected = true
    }

    fun setFavoriteVacancyNumber(number: Int) {
        if (number > 0) {
            binding.layoutBadgeFavorite.visibility = View.VISIBLE
            binding.titleFavoriteNumber.text = StringBuilder(number.toString())
        } else {
            binding.layoutBadgeFavorite.visibility = View.GONE
        }
    }


    private data class Item(val iconView: ImageView, val textView: TextView)

    interface OnItemClickListener {
        fun onSelectItem(item: BottomNavigationItem)
    }
}