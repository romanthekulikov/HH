package com.roman_kulikov.hh.ui.main.adapters.delegators

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.domain.entities.Offer
import com.roman_kulikov.hh.databinding.ItemOffersListBinding
import com.roman_kulikov.hh.ui.main.adapters.OfferAdapter
import com.roman_kulikov.hh.ui.main.adapters.items.OffersListItem
import javax.inject.Inject

class OffersListAdapterDelegate @Inject constructor(
    private val offerAdapter: OfferAdapter
) : AdapterDelegate<List<DisplayableItem>>() {

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is OffersListItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemOffersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OffersListViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as OffersListViewHolder
        val item = items[position] as OffersListItem

        viewHolder.bind(item.offersList)
    }

    inner class OffersListViewHolder(
        private val binding: ItemOffersListBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(offerList: List<Offer>) {
            offerAdapter.setContent(offerList)
            binding.recyclerViewOffers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewOffers.adapter = offerAdapter
        }
    }
}