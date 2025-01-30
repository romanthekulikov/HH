package com.roman_kulikov.hh.ui.main.adapters.delegators

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.domain.use_cases.DeclensionUseCase
import com.roman_kulikov.domain.use_cases.MonthUseCase
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.databinding.ItemVacancyBinding
import com.roman_kulikov.hh.peopleDeclensionMap
import javax.inject.Inject

class VacancyAdapterDelegate @Inject constructor(
    val declensionUseCase: DeclensionUseCase,
    val monthUseCase: MonthUseCase
) : AdapterDelegate<List<DisplayableItem>>() {
    init {
        HHApp.appComponent.inject(this)
    }

    lateinit var onItemClickListener: OnItemClickListener

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is Vacancy
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return VacancyViewHolder(ItemVacancyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as VacancyViewHolder
        val item = items[position] as Vacancy
        viewHolder.bind(item, position)
    }

    inner class VacancyViewHolder(private val binding: ItemVacancyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Vacancy, position: Int) {
            val lookingNumberDeclension = declensionUseCase(item.lookingNumber ?: 0)
            val peopleString = peopleDeclensionMap[lookingNumberDeclension]
            val publishMonth = item.publishedDate.month.value
            val monthString = monthUseCase(publishMonth)
            binding.apply {
                if ((item.lookingNumber ?: 0) > 0) {
                    titleLookingNumber.text = StringBuilder("Сейчас просматривает ${item.lookingNumber} $peopleString")
                }
                imageCheckFavorite.setOnClickListener {
                    imageCheckFavorite.isSelected = !imageCheckFavorite.isSelected
                    onItemClickListener.addVacancyToFavorite(item, position)
                }
                titleVacancyName.text = item.title
                titleTown.text = item.address.town
                titleCompany.text = item.company
                titleLayoutExperience.text = item.experience.previewText
                titlePublicationDate.text = StringBuilder("Опубликовано $publishMonth $monthString")
            }
        }

    }

    interface OnItemClickListener {
        fun addVacancyToFavorite(vacancy: Vacancy, position: Int)
    }
}