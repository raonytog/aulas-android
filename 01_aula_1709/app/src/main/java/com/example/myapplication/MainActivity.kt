package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonEntrar.setOnClickListener(this)
        binding.textRecoverPassword.setOnClickListener(this)
        binding.enviar.setOnClickListener(this)
        binding.imgbuttonLeft.setOnClickListener(this)
        binding.imgbuttonMid.setOnClickListener(this)
        binding.imgbuttonRight.setOnClickListener(this)
        binding.cliqueAqui.setOnClickListener(this)
    }

    override fun onClick(view : View) {
        var text : String? = " error !"
        val duration = Toast.LENGTH_SHORT

        if (view.id == R.id.button_entrar) {
            val user = binding.edittextUsername.text.toString()
            val password = binding.edittextPassword.text.toString()

            if (user.equals("goku") && password.equals("oieusougoku")) { text = "Login com sucesso! :)"}
            else { text = "Login sem sucesso! :("}
        }

        else if (view.id == R.id.text_recover_password) {
            text = binding.textRecoverPassword.text.toString()
        }

        else if (view.id == R.id.enviar) {
            text = binding.enviar.text.toString()
        }

        else if (view.id == R.id.imgbutton_left) {
            text = "botao da esquerda"
        }

        else if (view.id == R.id.imgbutton_mid) {
            text = "botao do meio"
        }

        else if (view.id == R.id.imgbutton_right) {
            text = "botao da direita"
        }

        else if (view.id == R.id.clique_aqui) {
            text = "vai se cadastrar hoje nao rsrs"
        }

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

// exercicio 1
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.buttonAdd.setOnClickListener(this)
//    }
//
//    override fun onClick(view : View) {
//        if (view.id == R.id.button_add) {
//            val text = binding.edittextInsertName.text
//            binding.textGreating.text = "Olá, ${text}!"
//        }
//    }

}