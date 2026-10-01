package com.example.aula_2909

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.aula_2909.databinding.ActivityMain2Binding

import java.util.Random

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMain2Binding;
    private lateinit var sp: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = Preferences(this, applicationContext)
        binding.textGreating.text = "Hello, ${sp.getString("name")}"
        binding.imgApple.setOnClickListener(this)
        binding.imgPizza.setOnClickListener(this)
        binding.buttonGerarFrase.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.img_apple) {
            binding.textRandomTxt.text = CuriosidadesAlimentares.getAboutApple()
            binding.imgApple.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
            binding.imgPizza.clearColorFilter()
        }

        if (view?.id == R.id.img_pizza) {
            binding.textRandomTxt.text = CuriosidadesAlimentares.getAboutPizza()
            binding.imgPizza.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
            binding.imgApple.clearColorFilter()
        }

        if (view?.id == R.id.button_gerar_frase) {
            binding.textRandomTxt.text = CuriosidadesAlimentares.getAllRandom()
        }
    }
}