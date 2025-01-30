package com.roman_kulikov.hh.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roman_kulikov.domain.entities.Offer
import com.roman_kulikov.hh.databinding.ItemOfferBinding
import com.roman_kulikov.hh.offerIconIdToResMap
import javax.inject.Inject

class OfferAdapter @Inject constructor() : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {
    private lateinit var offersList: List<Offer>

    fun setContent(offerList: List<Offer>) {
        this.offersList = offerList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = offersList.size

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(offersList[position])
    }

    inner class OfferViewHolder(private val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Offer) {
            binding.apply {
                if (item.offerId == null) {
                    imageIcOffer.visibility = View.GONE
                } else {
                    offerIconIdToResMap[item.offerId]?.let { imageIcOffer.setImageResource(it) }
                }

                titleOffer.text = item.title

                if (item.button == null) {
                    titleUp.visibility = View.GONE
                } else {
                    titleUp.visibility = View.VISIBLE
                    titleUp.text = item.button!!.text
                }
            }
        }
    }
}