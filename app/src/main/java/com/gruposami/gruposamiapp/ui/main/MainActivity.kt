package com.gruposami.gruposamiapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gruposami.gruposamiapp.R
import com.gruposami.gruposamiapp.databinding.ActivityMainBinding
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.ui.login.LoginActivity
import com.gruposami.gruposamiapp.ui.orden.OrdenActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val naranja = ContextCompat.getColor(applicationContext, R.color.naranja)
        val verde = ContextCompat.getColor(applicationContext, R.color.verde)
        val grisClaro = ContextCompat.getColor(applicationContext, R.color.gris_claro)

        binding.botonMedir.setBackgroundColor(grisClaro)
        binding.botonMedidas.setBackgroundColor(grisClaro)
        binding.botonMontar.setBackgroundColor(grisClaro)
        binding.botonMontadas.setBackgroundColor(grisClaro)

        swipeRefreshLayout = binding.swipeRefreshMain
        Sesion.filtroEstado = ""

        mainViewModel.init()

        mainViewModel.mensajeFlotante.observe(this, Observer {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
        })

        mainViewModel.mensaje.observe(this, Observer {
            binding.textView.text = "Bienvenido $it"
        })

        mainViewModel.cerrarSesionView.observe(this, Observer{
            if (it){
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
        })

        mainViewModel.numeroOrdenes.observe(this) {
            if (it.medir > 0) {
                binding.botonMedir.text = " Medir -> ${it.medir}"
                binding.botonMedir.setBackgroundColor(naranja)
            } else {
                binding.botonMedir.text = " Medir -> Sin Ordenes"
                binding.botonMedir.setBackgroundColor(grisClaro)
            }
            if (it.medido > 0) {
                binding.botonMedidas.text = " Medidas -> ${it.medido}"
                binding.botonMedidas.setBackgroundColor(verde)
            } else {
                binding.botonMedidas.text = " Medidas -> Sin Ordenes"
                binding.botonMedidas.setBackgroundColor(grisClaro)
            }
            if (it.montar > 0) {
                binding.botonMontar.text = " Montar -> ${it.montar}"
                binding.botonMontar.setBackgroundColor(naranja)
            } else {
                binding.botonMontar.text = " Montar -> Sin Ordenes"
                binding.botonMontar.setBackgroundColor(grisClaro)
            }
            if (it.montado > 0) {
                binding.botonMontadas.text = " Montadas -> ${it.montado}"
                binding.botonMontadas.setBackgroundColor(verde)
            } else {
                binding.botonMontadas.text = " Montadas -> Sin Ordenes"
                binding.botonMontadas.setBackgroundColor(grisClaro)
            }
        }

        mainViewModel.isLoading.observe(this) {
            swipeRefreshLayout.isRefreshing = it
        }

        swipeRefreshLayout.setOnRefreshListener {
//            getSignalStrengthPercentage(this) { signalStrengthPercentage ->
//                if (signalStrengthPercentage > 50) {
                    mainViewModel.sincronizarOrdenes()
//                } else {
//                    Toast.makeText(this, "No tienes suficiente cobertura.\nInténtalo más tarde.", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
        }

        binding.botonMedir.setOnClickListener {
            Sesion.filtroEstado = "Medir"
            startActivity(Intent(this@MainActivity, OrdenActivity::class.java))
        }

        binding.botonMedidas.setOnClickListener {
            Sesion.filtroEstado = "Medido"
            startActivity(Intent(this@MainActivity, OrdenActivity::class.java))
        }

        binding.botonMontar.setOnClickListener {
            Sesion.filtroEstado = "Montar"
            startActivity(Intent(this@MainActivity, OrdenActivity::class.java))
        }

        binding.botonMontadas.setOnClickListener {
            Sesion.filtroEstado = "Montado"
            startActivity(Intent(this@MainActivity, OrdenActivity::class.java))
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setCancelable(true)
                builder.setTitle("Cerrar sesión")
                builder.setMessage("¿Seguro que quieres cerrar sesión?")
                builder.setPositiveButton("Si") { _, _ ->
                    mainViewModel.cerrarSesion()
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                }
                builder.setNegativeButton("No") { dialog, _ ->
                    dialog.cancel()
                }
                builder.show()
            }
        })
    }

}