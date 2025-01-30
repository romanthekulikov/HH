package com.roman_kulikov.hh.ui.main.adapters.delegators

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.domain.use_cases.DeclensionUseCase
import com.roman_kulikov.hh.R
import com.roman_kulikov.hh.databinding.ItemSearchFieldBinding
import com.roman_kulikov.hh.ui.main.adapters.models.SearchViewItem
import com.roman_kulikov.hh.vacancyDeclensionMap
import javax.inject.Inject

class SearchFieldAdapterDelegate @Inject constructor(
    val declensionUseCase: DeclensionUseCase
) : AdapterDelegate<List<DisplayableItem>>() {
    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is SearchViewItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SearchFieldViewHolder(ItemSearchFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as SearchFieldViewHolder
        viewHolder.bind(items[position] as SearchViewItem)
    }

    inner class SearchFieldViewHolder(private val binding: ItemSearchFieldBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchViewItem) {
            if (item.fullMode) {
                binding.imageSearch.setImageResource(R.drawable.ic_back_arrow)
                binding.layoutVacancyNumber.visibility = View.VISIBLE
                val vacancyDeclension = vacancyDeclensionMap[declensionUseCase(item.vacancyNumber)]
                binding.titleVacancyNumber.text = StringBuilder("${item.vacancyNumber} $vacancyDeclension")
                binding.imageSearch.isClickable = true
                binding.imageSearch.setOnClickListener {
                    item.onBackClickListener.onBackClick()
                }
            } else {
                binding.imageSearch.isClickable = false
                binding.imageSearch.setImageResource(R.drawable.ic_search_unselected)
                binding.layoutVacancyNumber.visibility = View.GONE
            }
        }
    }

    interface OnClickListener {
        fun onBackClick()
    }
}