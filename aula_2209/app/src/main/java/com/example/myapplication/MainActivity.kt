package com.example.myapplication

import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalcular.setOnClickListener(this)
    }

    override fun onClick(view : View) {
        var text: String? = " error !"
        val duration = Toast.LENGTH_SHORT

        if (view.id == R.id.button_calcular) {
            var salario = binding.edittextSalario.text.toString().toFloat() ?: 0f
            var gastos = binding.editTextGastos.text.toString().toFloat() ?: 0f
            var dependentes = binding.editTextDependentes.text.toString().toFloat() ?: 0f

            text = "eba"
            binding.textZero.text = "R$ ${salario - gastos}"
        }

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}