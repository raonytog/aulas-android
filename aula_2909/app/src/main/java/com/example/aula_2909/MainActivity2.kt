package com.example.aula_2909

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aula_2909.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMain2Binding;
    private lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = applicationContext.getSharedPreferences("CHAVE_ACESSO", MODE_PRIVATE)
        binding.textHello.text = "Hello, ${sp.getString("name", "")}"
    }

    override fun onClick(view: View?) {
        TODO()
    }
}