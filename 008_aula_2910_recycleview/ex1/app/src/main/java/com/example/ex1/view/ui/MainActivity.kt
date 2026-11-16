package com.example.ex1.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ex1.R
import com.example.ex1.databinding.ActivityMainBinding
import com.example.ex1.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this)[MainViewModel::class.java]

        setObserver()
        setListener()
    }

    fun setObserver() {
        mainVM.getIsOnListPage().observe(this, Observer {
            startActivity(Intent(this, animal_list::class.java))
            finish()
        })

        mainVM.getToastError().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }

    fun setListener() {
        binding.imgAdd.setOnClickListener(this)
        binding.imgList.setOnClickListener(this)
        binding.buttonAddAnimal.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_add_animal -> { mainVM.addAnimal( binding.editAnimaName.text.toString() ) }
            R.id.img_list -> { mainVM.setIsOnListPage(true) }
        }
    }
}