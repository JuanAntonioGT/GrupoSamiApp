package com.gruposami.gruposamiapp.ui.firma

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruposami.gruposamiapp.databinding.FormularioFirmaBinding
import com.gruposami.gruposamiapp.domain.firma.model.Firma
import com.gruposami.gruposamiapp.domain.multimedia.model.Multimedia
import com.gruposami.gruposamiapp.domain.orden.model.Orden
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.ui.orden.OrdenActivity
import com.gruposami.gruposamiapp.ui.orden.adapters.ServicioAdapter
import com.gruposami.gruposamiapp.utils.GuardarDibujo
import com.gruposami.gruposamiapp.utils.timestamp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirmaActivity : AppCompatActivity() {
    // TODO: Diferenciar entre texto de medir y el texto de montar

    private lateinit var binding: FormularioFirmaBinding
    private val firmaViewModel: FirmaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormularioFirmaBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        lateinit var listaServicios: List<Servicio>
        lateinit var orden: Orden
        val ordenId = intent.getIntExtra("orden_id", 0)

        // Para que se limpie la firma al pulsar Borrar
        binding.botonLimpiar.setOnClickListener {
            binding.signaturepad.clear()
        }

        firmaViewModel.obtenerOrden(ordenId)
        firmaViewModel.orden.observe(this) {
            orden = it
        }

        firmaViewModel.obtenerServicios(ordenId)
        firmaViewModel.listado_servicios.observe(this, Observer { it ->
            listaServicios = it.map { it.servicio }
            binding.recyclerFirmando.adapter = ServicioAdapter(it, false, null) // falta meter el it
            binding.recyclerFirmando.layoutManager = LinearLayoutManager(this)
            binding.recyclerFirmando.itemAnimator = DefaultItemAnimator()
        }
        )

        if (!binding.checkBox1.isChecked) {
            binding.checkBox1.isChecked = true
        }

        binding.botonCancelar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Cancelar")
                .setMessage("\n ¿Cancelar la firma?")
                .setPositiveButton(
                    "Confirmar"
                ) { _: DialogInterface?, _: Int ->
                    startActivity(Intent(this, OrdenActivity::class.java))
                }
                .setNegativeButton(
                    "Cancelar"
                ) { _: DialogInterface?, _: Int -> }.show()
        }

        binding.botonSiguiente.setOnClickListener {

            val conformidad = binding.checkBox1.isChecked
            val estetica = binding.checkBox2.isChecked
            val indemnizacion = binding.checkBox3.isChecked
            val comentario = binding.editText.text.toString()

            for (serv in listaServicios) {
                val multimediaFirma: Multimedia = GuardarDibujo(
                    binding.root,
                    serv,
                    binding.signaturepad.signatureBitmap,
                    "Firma",
                    Sesion.filtroEstado
                )

                if (serv.medido == true) {

                    val firma = Firma(
                        id = System.currentTimeMillis().toInt(),
                        tipoFirma = Sesion.filtroEstado,
                        conformidad = conformidad,
                        estetica = estetica,
                        indemnizacion = indemnizacion,
                        comentario = comentario,
                        multimedia = multimediaFirma.id,
                        fechaCreacion = timestamp(),
                        fechaModificacion = timestamp(),
                        servicioId = serv.id,
                    )
                    this.firmaViewModel.insertarFirma(firma)

                }
            }

            firmaViewModel.modificarOrden(orden = orden)
            this.startActivity(Intent(this, OrdenActivity::class.java))
        }

    }

    // Cuando hago el viaje a la lista, me salta error yno se muy bien porque.
    // Creo que es por tener la memoria a tope. ESTO LO HA SOLUCIONADO
    override fun onSaveInstanceState(oldInstanceState: Bundle) {
        super.onSaveInstanceState(oldInstanceState)
        oldInstanceState.clear()
    }

}