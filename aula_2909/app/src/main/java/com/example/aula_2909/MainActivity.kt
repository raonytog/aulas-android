package com.example.aula_2909

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aula_2909.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var sp : SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = applicationContext.getSharedPreferences("CHAVE_ACESSO", MODE_PRIVATE)

        binding.buttonView1Guardar.setOnClickListener(this)

    }

    override fun onClick(view : View) {
        if (view.id == R.id.button_view1_guardar) {
            sp.edit().putString("name", binding.editView1Name.text.toString()).apply()

            startActivity(
                Intent(this, MainActivity2::class.java)
            )
        }
    }
}