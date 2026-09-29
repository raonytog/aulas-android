package com.example.aula_2909

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.aula_2909.databinding.ActivityMain2Binding

import java.util.Random

val random = Random()
fun rand(from: Int, to: Int) : Int {
    return random.nextInt(to - from) + from
}

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMain2Binding;
    private lateinit var sp : SharedPreferences
    private var lista_maca : List<String> = mutableListOf(
        "maças sao boas para a saude",
        "macas sao vermelhas as vezes, depende do dia",
        "uma maca caiu na cabeca de newton"
    )

    private var lista_pizza : List<String> = mutableListOf(
        "pizzas nao sao boas para a saude",
        "pizzas sao vermelhas as vezes, depende do dia",
        "uma pizza NAO caiu na cabeca de newton"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = applicationContext.getSharedPreferences("CHAVE_ACESSO", MODE_PRIVATE)
        binding.textGreating.text = "Hello, ${sp.getString("name", "")}"
        binding.imgApple.setOnClickListener( this )
        binding.imgPizza.setOnClickListener( this )
        binding.buttonGerarFrase.setOnClickListener( this )
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.img_apple) {
            binding.textRandomTxt.text = lista_maca.get( rand(0, 2) )
            binding.imgApple.setColorFilter( ContextCompat.getColor(this, R.color.yellow) )
            binding.imgPizza.clearColorFilter()
        }

        if (view?.id == R.id.img_pizza) {
            binding.textRandomTxt.text = lista_pizza.get( rand(0, 2) )
            binding.imgPizza.setColorFilter( ContextCompat.getColor(this, R.color.yellow) )
            binding.imgApple.clearColorFilter()
        }

        if (view?.id == R.id.button_gerar_frase) {
            var r = rand(0, 2)

            if (r == 0) { binding.textRandomTxt.text = lista_pizza.get(rand(0, 2)) }
            else { binding.textRandomTxt.text = lista_maca.get(rand(0, 2))  }
        }
    }
}