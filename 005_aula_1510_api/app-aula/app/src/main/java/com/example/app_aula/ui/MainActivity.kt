package com.example.aula_2909

import android.content.Intent
import android.database.Observable
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

        binding.buttonKeep.setOnClickListener(this)
    }

    private fun setObserver() {
        mainVM.getPostContent().observe(this, Observer {
            if (it != "") binding.textTop.text = it
            else binding.textTop.text = getString(R.string.default_post)
        })
    }

    override fun onClick(view : View) {
        val duration = Toast.LENGTH_SHORT

        when (view.id) {
            R.id.button_keep -> {
                val id = binding.editText.text.toString()

                if (id == "") Toast.makeText(this, R.string.empty_id, duration).show()
                else mainVM.requestPost( binding.editText.text.toString() )
            }
        }


    }
}