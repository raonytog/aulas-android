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
            var salario = binding.edittextSalario.text.toString().toFloatOrNull() ?: 0F
            var gastos = binding.editTextGastos.text.toString().toFloatOrNull() ?: 0F
            var dependentes = binding.editTextDependentes.text.toString().toFloatOrNull() ?: 0F

            var imposto = 0F
            if (salario > 5_000) {
                imposto = salario - gastos - (189.59F*dependentes)
                imposto = imposto * 0.275F
                text = "Imposto calculado!"
            }
            else { text = "Imposto não calculado!" }

            binding.textZero.text = "${imposto}"
        }

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}