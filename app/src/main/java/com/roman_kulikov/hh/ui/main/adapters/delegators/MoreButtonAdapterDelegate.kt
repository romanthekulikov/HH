package com.roman_kulikov.hh.ui.main.adapters.delegators

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.domain.use_cases.DeclensionUseCase
import com.roman_kulikov.hh.databinding.ItemMoreButtonBinding
import com.roman_kulikov.hh.ui.main.adapters.models.MoreButtonItem
import com.roman_kulikov.hh.vacancyDeclensionMap
import javax.inject.Inject

class MoreButtonAdapterDelegate @Inject constructor(
    val declensionUseCase: DeclensionUseCase
) : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is MoreButtonItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MoreButtonViewHolder(ItemMoreButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as MoreButtonViewHolder
        val item = items[position] as MoreButtonItem

        viewHolder.bind(item)
    }

    inner class MoreButtonViewHolder(private val binding: ItemMoreButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoreButtonItem) {
            val title = "Еще ${item.vacancyNumber} ${vacancyDeclensionMap[declensionUseCase(item.vacancyNumber)]}"
            binding.buttonMore.text = title
            binding.buttonMore.setOnClickListener {
                item.onClickListener.onClick()
            }
        }
    }

    interface OnClickListener {
        fun onClick()
    }
}