package com.example.aula_2909

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aula_2909.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()
        setListeners()


        mainVM.setup_1()
    }

    fun setObserver() {
        mainVM.getNavigateJump().observe(this, Observer {
            if (it) {
                startActivity(Intent(this, MainActivity2::class.java))
                finish()
            }
        })
    }

    fun setListeners() {
        binding.buttonView1Guardar.setOnClickListener(this)
    }


    override fun onClick(view : View?) {
        when (view?.id) {
            R.id.button_view1_guardar -> mainVM.validateName(binding.editView1Name.text.toString())
        }
    }
}