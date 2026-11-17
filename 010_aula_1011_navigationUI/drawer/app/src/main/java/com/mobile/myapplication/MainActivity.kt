package com.mobile.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.mobile.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainContent) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setBinding()
        setToolbarAndNavigation()
    }

    fun adjustPadding() {
        val paddingExtra = (24 * resources.displayMetrics.density).toInt()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left + paddingExtra, systemBars.top + paddingExtra,
                systemBars.right + paddingExtra, systemBars.bottom + paddingExtra
            )
            insets
        }
    }

    fun setBinding() {
        binding.toolbar.setOnMenuItemClickListener({
            when (it.itemId) {
                R.id.search -> { Toast.makeText(this, "Botao de busca", Toast.LENGTH_SHORT).show()  }
                R.id.info -> { Toast.makeText(this, "Botao de info", Toast.LENGTH_SHORT).show()  }
            }
            true
        })
    }

    fun setToolbarAndNavigation() {
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFrag.navController

        binding.navigationDrawer.setupWithNavController(navController)
        binding.bottomNavMenu.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }

    override fun onClick(view: View) {
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        when (view.id) {
            R.id.button_cara -> { navHostFrag.navController.navigate(R.id.action_initFragment_to_oneFragment) }
            R.id.button_coroa -> { navHostFrag.navController.navigate(R.id.action_initFragment_to_twoFragment) }

        }
    }
}
