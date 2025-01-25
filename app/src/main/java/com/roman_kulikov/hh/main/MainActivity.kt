package com.roman_kulikov.hh.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.roman_kulikov.hh.HHApp
import com.roman_kulikov.hh.R
import com.roman_kulikov.hh.base.BaseActivity
import com.roman_kulikov.hh.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: select best way to reactive update ui: Flow(UiState), LiveData
        lifecycleScope.launch {
            viewModel.state.collectLatest {
                binding.nameText.text = it.name
            }
        }

        binding.testButton.setOnClickListener {
            viewModel.changeName()
        }
    }
}