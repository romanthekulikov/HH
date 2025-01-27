package com.roman_kulikov.hh.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BaseState> : AppCompatActivity() {
    protected abstract val viewModel: BaseViewModel<T>

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stopJob()
    }
}