package com.example.myapplication.view.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCadastroBinding
import com.example.myapplication.view.viewModel.mainViewModel

class CadastroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var mainVM: mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val paddingPx = (24 * resources.displayMetrics.density).toInt()
            v.setPadding(
                systemBars.left + paddingPx,
                systemBars.top + paddingPx,
                systemBars.right + paddingPx,
                systemBars.bottom + paddingPx
            )
            insets
        }

        mainVM = ViewModelProvider(this)[mainViewModel::class.java]

        binding.ibBack.setOnClickListener(this)
        binding.buttonRegistrar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.ib_back -> finish()
            R.id.button_registrar -> {
                val email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword1.text.toString().trim()
                val passwordConfirm = binding.etPassword2.text.toString().trim()

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, getString(R.string.toast_fill_email_password), Toast.LENGTH_SHORT).show()
                    return
                }

                if (password != passwordConfirm) {
                    Toast.makeText(this, getString(R.string.toast_password_mismatch), Toast.LENGTH_SHORT).show()
                    return
                }

                if (password.length < 6) {
                    Toast.makeText(this, getString(R.string.toast_password_too_short), Toast.LENGTH_SHORT).show()
                    return
                }

                mainVM.registraUser(email, password) { success, errorMessage ->
                    if (success) {
                        Toast.makeText(this, getString(R.string.toast_register_success), Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, errorMessage ?: getString(R.string.toast_register_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}