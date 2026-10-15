package com.example.aula_2909

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aula_2909.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var sp : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = Preferences(this, applicationContext)
        if (sp.getString("name") != "") {
            startActivity(
                Intent(this, MainActivity2::class.java)
            )
        }
        binding.buttonView1Guardar.setOnClickListener(this)

    }

    override fun onClick(view : View) {
        val duration = Toast.LENGTH_SHORT

        if (view.id == R.id.button_view1_guardar) {
            if (binding.editView1Name.text.toString().isEmpty()) {
                val toast = Toast.makeText(applicationContext, getString(R.string.nome_vazio), duration)
                toast.show()
            }

            else if (binding.editView1Name.text.toString().length < 3) {
                val toast = Toast.makeText(applicationContext, getString(R.string.palavra_pequena), duration)
                toast.show()
            }

            else {
                sp.setString("name", binding.editView1Name.text.toString())
                val toast = Toast.makeText(applicationContext, getString(R.string.movendo), duration)
                toast.show()

                startActivity(
                    Intent(this, MainActivity2::class.java)
                )
            }
        }
    }
}