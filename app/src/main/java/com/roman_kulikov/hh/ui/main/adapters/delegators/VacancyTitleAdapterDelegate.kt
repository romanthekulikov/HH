package com.roman_kulikov.hh.ui.main.adapters.delegators

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.hh.databinding.ItemVacancyTitleBinding
import com.roman_kulikov.hh.ui.main.adapters.models.VacancyTitle
import javax.inject.Inject

class VacancyTitleAdapterDelegate @Inject constructor() : AdapterDelegate<List<DisplayableItem>>() {
    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is VacancyTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return VacancyTitleViewHolder(ItemVacancyTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as VacancyTitleViewHolder
        val title = (items[position] as VacancyTitle).title

        viewHolder.bind(title)
    }

    inner class VacancyTitleViewHolder(private val binding: ItemVacancyTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.titleVacancy.text = item
        }
    }
}