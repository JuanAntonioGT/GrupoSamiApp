package com.gruposami.gruposamiapp.ui.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.gruposami.gruposamiapp.databinding.ActivityLoginBinding
import com.gruposami.gruposamiapp.network.model.LoginRequest
import com.gruposami.gruposamiapp.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        comprobarTodosPermisos()

        loginViewModel.init()

        loginViewModel.mostrarTarjeta.observe(this, Observer {
            binding.cardViewContainer.isVisible = it
            binding.textViewErrores.text = ""
        })

        loginViewModel.cargando.observe(this, Observer {
            binding.loading.isVisible = it
            binding.textViewErrores.text = ""
        })

        loginViewModel.mensaje.observe(this, Observer {
            Toast.makeText(this@LoginActivity, it, Toast.LENGTH_LONG).show()
        })

        loginViewModel.error.observe(this, Observer {
            binding.textViewErrores.text = it
        })

        loginViewModel.logueado.observe(this, Observer {
            if (it == true) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        })

        loginViewModel.usuario.observe(this, Observer {
            binding.editTextUsuario.setText(it)
        })

        binding.buttonEntrar.setOnClickListener {
            val loginRequest = LoginRequest(
                binding.editTextUsuario.text.toString(),
                binding.editTextContrasena.text.toString()
            )
            loginViewModel.comprobacionLogin(loginRequest)
        }

        /* Método para crear un díalogo que te pregunte antes de salir */
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val builder = AlertDialog.Builder(this@LoginActivity)
                builder.setMessage("¿Deseas salir de la aplicación?")
                    .setCancelable(false)
                    .setPositiveButton("Si") { _, _ -> finishAffinity() }
                    .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
                val alert = builder.create()
                alert.show()
            }
        }
        )

    }

    private fun comprobarTodosPermisos() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // El permiso ya ha sido otorgado
            Log.i("LoginActivity", "Ya tienes permiso de internet")
        } else {
            // El permiso no ha sido otorgado, se debe solicitar al usuario
            Log.i("LoginActivity", "No tienes permiso de internet")

            requestPermissions(
                arrayOf(
                    Manifest.permission.INTERNET,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CALL_PHONE,
                ),
                0
            )
        }

    }

}