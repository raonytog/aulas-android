package com.example.aula_2909

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aula_2909.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var sp: Preferences

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
        var text: String? = " error !"
        val duration = Toast.LENGTH_SHORT

        if (view.id == R.id.button_view1_guardar) {
            if (binding.editView1Name.text.toString().isEmpty()) {
                val toast = Toast.makeText(applicationContext, "Nome vazio!", duration)
                toast.show()
            }

            else {
                sp.setString("name", binding.editView1Name.text.toString())
                text = "Indo para a outra tela..."


                startActivity(
                    Intent(this, MainActivity2::class.java)
                )
            }
        }
    }
}