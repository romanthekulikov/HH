package com.roman_kulikov.hh.ui.vacancy_card

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.roman_kulikov.domain.entities.Vacancy
import com.roman_kulikov.domain.use_cases.DeclensionUseCase
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.VibrationServicesHelper
import com.roman_kulikov.hh.databinding.ActivityVacancyCardBinding
import com.roman_kulikov.hh.getVibrator
import com.roman_kulikov.hh.peopleDeclensionMap
import com.roman_kulikov.hh.ui.base.BaseActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class VacancyCardActivity : BaseActivity<VacancyCardState>(), VibrationServicesHelper {
    @Inject
    lateinit var declensionUseCase: DeclensionUseCase

    private lateinit var binding: ActivityVacancyCardBinding
    override val viewModel: VacancyCardViewModel by viewModels()
    override val vibrator: Vibrator by lazy { getVibrator(this) }

    init {
        HHApp.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVacancyCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()

        val vacancyId = intent.extras?.getString(EXTRA_VACANCY_ID)
        vacancyId?.let { viewModel.getVacancy(it) }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                applyBinding(state.vacancy)
            }
        }
    }

    private fun applyBinding(vacancy: Vacancy?) {
        binding.layoutVacancy.visibility = if (vacancy == null) View.GONE else View.VISIBLE
        binding.imageCheckFavorite.visibility = if (vacancy == null) View.GONE else View.VISIBLE
        binding.titleStub.visibility = if (vacancy == null) View.VISIBLE else View.GONE

        if (vacancy == null) {
            return
        }
        binding.apply {
            val reverseSelected = { imageCheckFavorite.isSelected = !imageCheckFavorite.isSelected }
            imageCheckFavorite.isSelected = vacancy.isFavorite
            imageCheckFavorite.setOnClickListener {
                withVibration {
                    reverseSelected()
                }
            }

            titleVacancyName.text = vacancy.title
            titleSalary.text = vacancy.salary.full
            titleExperience.text = StringBuilder("Требуемый опыт: ${vacancy.experience.text}")
            titleSchedules.text = vacancy.schedules.joinToString(", ")
            if (vacancy.appliedNumber == null) {
                titleAppliedNumber.text = "Будьте первым!"
            } else {
                val peopleText = peopleDeclensionMap[declensionUseCase(vacancy.appliedNumber!!)]!!
                titleAppliedNumber.text = StringBuilder("${vacancy.appliedNumber} $peopleText уже откликнулись")
            }
            if (vacancy.lookingNumber == null) {
                layoutLook.alpha = 0f
            } else {
                val peopleText = peopleDeclensionMap[declensionUseCase(vacancy.lookingNumber!!)]!!
                titleLookingNumber.text = StringBuilder("${vacancy.lookingNumber} $peopleText сейчас смотрят")
            }

            imageBack.setOnClickListener {
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_VACANCY_ID = "extra_vacancy_id"

        fun createIntent(fromContext: Context, vacancyId: String): Intent {
            val intent = Intent(fromContext, VacancyCardActivity::class.java)
            intent.putExtra(EXTRA_VACANCY_ID, vacancyId)

            return intent
        }
    }
}