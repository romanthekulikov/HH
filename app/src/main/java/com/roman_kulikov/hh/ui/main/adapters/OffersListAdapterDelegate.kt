package com.roman_kulikov.hh.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.roman_kulikov.domain.entities.DisplayableItem
import com.roman_kulikov.domain.entities.Offer
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.databinding.ItemOffersListBinding
import javax.inject.Inject

class OffersListAdapterDelegate @Inject constructor() : AdapterDelegate<List<DisplayableItem>>() {

    init {
        HHApp.appComponent.inject(this)
    }

    @Inject
    lateinit var offerAdapter: OfferAdapter

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        if (items[position] is List<*>) {
            return (items[position] as List<*>).first() is Offer
        }

        return false
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return OffersListViewHolder(ItemOffersListBinding.inflate(LayoutInflater.from(parent.context)), parent.context)
    }

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as OffersListViewHolder
        val item = (items[position] as List<*>).filterIsInstance<Offer>()
        viewHolder.bind(item)
    }

    inner class OffersListViewHolder(
        private val binding: ItemOffersListBinding, private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: List<Offer>) {
            offerAdapter.setContent(item)
            binding.recyclerViewOffers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewOffers.adapter = offerAdapter
        }
    }
}