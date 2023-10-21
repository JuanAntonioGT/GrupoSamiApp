package com.gruposami.gruposamiapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gruposami.gruposamiapp.databinding.ActivityMainBinding
import com.gruposami.gruposamiapp.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val mainViewModel: MainViewModel by viewModels()
//    private val naranja = ContextCompat.getColor(applicationContext, R.color.naranja)
//    private val verde = ContextCompat.getColor(applicationContext, R.color.verde)
//    private val grisClaro = ContextCompat.getColor(applicationContext, R.color.gris_claro)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Permisos().comprobarTodosPermisos(this@MainActivity, this)

//        swipeRefreshLayout = binding.swipeRefreshMain
//        Sesion.filtroEstado = ""
//
//        mainViewModel.mensaje_flotante.observe(this, Observer {
//            Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
//        })
//
//        mainViewModel.mensaje.observe(this, Observer {
//            binding.textView.text = "Bienvenido $it"
//        })
//
//        mainViewModel.numeroOrdenes.observe(this) {
//            if (it.medir > 0) {
//                binding.botonMedir.text = " Medir -> $it"
//                binding.botonMedir.setBackgroundColor(naranja)
//            } else {
//                binding.botonMedir.text = " Medir -> Sin Ordenes"
//                binding.botonMedir.setBackgroundColor(grisClaro)
//            }
//            if (it.medido > 0) {
//                binding.botonMedidas.text = " Medidas -> $it"
//                binding.botonMedidas.setBackgroundColor(verde)
//            } else {
//                binding.botonMedidas.text = " Medidas -> Sin Ordenes"
//                binding.botonMedidas.setBackgroundColor(grisClaro)
//            }
//            if (it.montar > 0) {
//                binding.botonMontar.text = " Montar -> $it"
//                binding.botonMontar.setBackgroundColor(naranja)
//            } else {
//                binding.botonMontar.text = " Montar -> Sin Ordenes"
//                binding.botonMontar.setBackgroundColor(grisClaro)
//            }
//            if (it.montado > 0) {
//                binding.botonMontadas.text = " Montadas -> $it"
//                binding.botonMontadas.setBackgroundColor(verde)
//            } else {
//                binding.botonMontadas.text = " Montadas -> Sin Ordenes"
//                binding.botonMontadas.setBackgroundColor(grisClaro)
//            }
//        }
//
//        mainViewModel.isLoading.observe(this) {
//            swipeRefreshLayout.isRefreshing = it
//        }
////
//        swipeRefreshLayout.setOnRefreshListener {
//            getSignalStrengthPercentage(this) { signalStrengthPercentage ->
//                if (signalStrengthPercentage > 50) {
//                    mainViewModel.sincronizarOrdenes()
//                } else {
//                    Toast.makeText(this, "No tienes suficiente cobertura.\nInténtalo más tarde.", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//        }
//
//        binding.botonMedir.setOnClickListener {
//            Sesion.filtro_estado = "Medir"
//            startActivity(Intent(this@MainActivity, OrdenActivity::class.java))
//        }
//
//        binding.botonMedidas.setOnClickListener {
//            Sesion.filtro_estado = "Medido"
//            startActivity(Intent(this@MainActivity, OrdenActivity::class.java))
//        }
//
//        binding.botonMontar.setOnClickListener {
//            Sesion.filtro_estado = "Montar"
//            startActivity(Intent(this@MainActivity, OrdenActivity::class.java))
//        }
//
//        binding.botonMontadas.setOnClickListener {
//            Sesion.filtro_estado = "Montado"
//            startActivity(Intent(this@MainActivity, OrdenActivity::class.java))
//        }
//
//
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