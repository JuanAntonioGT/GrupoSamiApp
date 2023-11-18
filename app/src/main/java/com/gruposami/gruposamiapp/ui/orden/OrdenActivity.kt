package com.gruposami.gruposamiapp.ui.orden

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruposami.gruposamiapp.databinding.ActivityOrdenBinding
import com.gruposami.gruposamiapp.ui.main.MainActivity
import com.gruposami.gruposamiapp.ui.orden.adapters.OrdenAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdenActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityOrdenBinding
    private val ordenViewModel: OrdenViewModel by viewModels()
    private lateinit var adapter: OrdenAdapter
    private val llmanager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ordenViewModel.onCreate()

        ordenViewModel.listadoOrdenes.observe(this) {
            adapter = OrdenAdapter(
                ordenList = it,
                ordenViewModel = ordenViewModel
            )
            binding.recyclerViewOrdenes.layoutManager = llmanager
            binding.recyclerViewOrdenes.adapter = adapter
        }

        ordenViewModel.mensajeFlotante.observe(this) {
            Toast.makeText(this@OrdenActivity, it, Toast.LENGTH_LONG).show()
        }

        onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    startActivity(Intent(this@OrdenActivity, MainActivity::class.java))
                }
            }
        )
    }
}