package com.example.aula_2010.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.aula_2010.R
import com.example.aula_2010.
import com.example.estudo.viewModel.MainViewModel

class SearchCEP : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainVM: MainViewModel
    private lateinit var binding :

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()
        setListener()

    }

    fun setObserver() {

    }

    fun setListener() {

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_search -> mainVM.requestCep( binding )
        }
    }
}