package com.example.aula_2010.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.aula_2010.R
import com.example.aula_2010.databinding.ActivityInformationsBinding
import com.example.estudo.viewModel.MainViewModel

class InformationsActivity : AppCompatActivity() {
    private lateinit var mainVM: MainViewModel
    private lateinit var binding : ActivityInformationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()
        setListener()

    }

    fun setObserver() {

    }

    fun setListener() {

    }
}