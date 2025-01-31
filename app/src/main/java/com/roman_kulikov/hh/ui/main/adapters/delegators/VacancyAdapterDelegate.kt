package com.roman_kulikov.hh.ui.main.adapters.delegators

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.domain.use_cases.DeclensionUseCase
import com.roman_kulikov.domain.use_cases.MonthUseCase
import com.roman_kulikov.hh.databinding.ItemVacancyBinding
import com.roman_kulikov.hh.peopleDeclensionMap
import javax.inject.Inject

class VacancyAdapterDelegate @Inject constructor(
    val declensionUseCase: DeclensionUseCase,
    val monthUseCase: MonthUseCase
) : AdapterDelegate<List<DisplayableItem>>() {
    lateinit var onVacancyClickListener: OnVacancyClickListener

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is Vacancy
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemVacancyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: List<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as VacancyViewHolder
        val item = items[position] as Vacancy
        viewHolder.bind(item)
    }

    inner class VacancyViewHolder(private val binding: ItemVacancyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Vacancy) {
            val lookingNumberDeclension = declensionUseCase(item.lookingNumber ?: 0)
            val peopleString = peopleDeclensionMap[lookingNumberDeclension]
            val publishMonth = item.publishedDate.month.value
            val monthString = monthUseCase(publishMonth)
            val updateFavorite = {
                binding.imageCheckFavorite.isSelected = !binding.imageCheckFavorite.isSelected
                item.isFavorite = !item.isFavorite
            }
            binding.apply {
                layout.setOnClickListener {
                    onVacancyClickListener.onVacancyClick(item)
                }

                if (item.lookingNumber != null) {
                    titleLookingNumber.visibility = View.VISIBLE
                    titleLookingNumber.text = StringBuilder("Сейчас просматривает ${item.lookingNumber} $peopleString")
                } else {
                    titleLookingNumber.visibility = View.GONE
                }

                imageCheckFavorite.isSelected = item.isFavorite
                imageCheckFavorite.setOnClickListener {
                    updateFavorite()
                    onVacancyClickListener.saveVacancy(item, updateFavorite)
                }

                titleVacancyName.text = item.title
                item.salary.short?.let { titleSalary.text = it } ?: titleSalary.setVisibility(View.GONE)
                titleTown.text = item.address.town
                titleCompany.text = item.company
                titleLayoutExperience.text = item.experience.previewText
                titlePublicationDate.text = StringBuilder("Опубликовано ${item.publishedDate.dayOfMonth} $monthString")
                buttonResponse.setOnClickListener { /* nothing */ }
            }
        }

    }

    interface OnVacancyClickListener {
        fun saveVacancy(vacancy: Vacancy, reverseEvent: () -> Unit)
        fun onVacancyClick(vacancy: Vacancy)
    }
}