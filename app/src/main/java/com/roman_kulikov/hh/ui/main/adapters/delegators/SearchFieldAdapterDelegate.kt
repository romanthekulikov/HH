package com.roman_kulikov.hh.ui.main.adapters.delegators

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.roman_kulikov.domain.DisplayableItem
import com.roman_kulikov.domain.use_cases.DeclensionUseCase
import com.roman_kulikov.hh.R
import com.roman_kulikov.hh.databinding.ItemSearchFieldBinding
import com.roman_kulikov.hh.ui.main.adapters.items.SearchViewItem
import com.roman_kulikov.hh.vacancyDeclensionMap
import javax.inject.Inject

class SearchFieldAdapterDelegate @Inject constructor(
    val declensionUseCase: DeclensionUseCase
) : AdapterDelegate<List<DisplayableItem>>() {

    lateinit var onClickListener: OnClickListener

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is SearchViewItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemSearchFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchFieldViewHolder(binding, parent.context)
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

    inner class SearchFieldViewHolder(
        private val binding: ItemSearchFieldBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        private fun textWatcher(item: SearchViewItem) = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { /* nothing */ }

            override fun afterTextChanged(p0: Editable?) { /* nothing */ }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onClickListener.setSearchText(text.toString())
                item.text = text.toString()
            }
        }

        fun bind(searchViewItem: SearchViewItem) {
            binding.inputQuery.setText(searchViewItem.text)
            binding.inputQuery.addTextChangedListener(textWatcher(searchViewItem))
            binding.inputQuery.setOnEnterClickListener { onClickListener.onEnterClick() }

            if (searchViewItem.fullMode) {
                binding.imageSearch.setImageResource(R.drawable.ic_back_arrow)
                binding.imageSearch.isClickable = true
                binding.imageSearch.setOnClickListener { onClickListener.onBackClick() }
                binding.inputQuery.hint = context.getString(R.string.hint_input_query_full_mode)

                val vacancyDeclension = vacancyDeclensionMap[declensionUseCase(searchViewItem.vacancyNumber)]
                binding.layoutVacancyNumber.visibility = View.VISIBLE
                binding.titleVacancyNumber.text = StringBuilder("${searchViewItem.vacancyNumber} $vacancyDeclension")
            } else {
                binding.imageSearch.isClickable = false
                binding.imageSearch.setImageResource(R.drawable.ic_search_unselected)
                binding.inputQuery.hint = context.getString(R.string.hint_input_query_limit_mode)

                binding.layoutVacancyNumber.visibility = View.GONE
            }
        }

        private fun EditText.setOnEnterClickListener(action: () -> Unit) {
            this.setOnKeyListener { _, keyCode, event ->
                if (event != null) {
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        action()
                        this.clearFocus()
                        val inputMethodManager =
                            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
                    }
                }

                false
            }
        }
    }

    interface OnClickListener {
        fun onBackClick()
        fun onEnterClick()
        fun setSearchText(text: String)
    }
}