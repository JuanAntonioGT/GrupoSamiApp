package com.gruposami.gruposamiapp.ui.orden.adapters


import android.content.DialogInterface
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gruposami.gruposamiapp.databinding.ItemOrdenBinding
import com.gruposami.gruposamiapp.domain.contacto.model.Contacto
import com.gruposami.gruposamiapp.domain.direccion.model.Direccion
import com.gruposami.gruposamiapp.domain.estado.model.Estado
import com.gruposami.gruposamiapp.domain.orden.model.OrdenCompleta
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.servicio.model.ServicioCompleto
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.ui.orden.OrdenViewModel
import com.gruposami.gruposamiapp.utils.timestamp
import okhttp3.internal.notifyAll


class OrdenViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    val binding = ItemOrdenBinding.bind(view)

    fun render(orden: OrdenCompleta, ordenViewModel: OrdenViewModel) {
        val colorNaranja = Color.parseColor("#FAA307")
        val colorVerde = Color.parseColor("#43A047")

        if (Sesion.filtroEstado.equals("Medir") || Sesion.filtroEstado.equals("Montar")) {
            binding.textViewEstado.setBackgroundColor(colorNaranja)
        } else {
            binding.textViewEstado.setBackgroundColor(colorVerde)
        }

        // Visibles - Invisibles
        binding.linearCliente.visibility = View.GONE
        binding.linearContactos.visibility = View.GONE
        binding.linearServicios.visibility = View.GONE
        binding.linearLayoutBotonMedicion.visibility = View.GONE

        // Para que al iniciar se quede solo el primer linear del adapter+
        binding.constraintTitulo.setOnClickListener {
            if (binding.linearCliente.visibility == View.GONE &&
                binding.linearContactos.visibility == View.GONE &&
                binding.linearServicios.visibility == View.GONE &&
                binding.linearLayoutBotonMedicion.visibility == View.GONE
            ) {
                binding.linearCliente.visibility = View.VISIBLE
                binding.linearContactos.visibility = View.VISIBLE
                binding.linearServicios.visibility = View.VISIBLE
                binding.linearLayoutBotonMedicion.visibility = View.VISIBLE
            } else {
                binding.linearCliente.visibility = View.GONE
                binding.linearContactos.visibility = View.GONE
                binding.linearServicios.visibility = View.GONE
                binding.linearLayoutBotonMedicion.visibility = View.GONE
            }
        }

        // Tarjeta Superior
        try {
            binding.textViewEstado.text = Sesion.filtroEstado.toString()
            binding.textViewPoblacion.text = orden.direccion.last().poblacion
            binding.textViewDireccion.text = orden.direccion.last().direccion
        } catch (e: Exception) {
            binding.textViewEstado.text = "Error"
            binding.textViewPoblacion.text = "Error"
            binding.textViewDireccion.text = "Error"
        }

        binding.textViewCodigo.text = "${orden.orden.codigoReferencia} ${orden.orden.numeroReferencia}"

        // Datos personales de contacto
        if (orden.cliente.razonSocial?.isNotEmpty() == true) {
            binding.textViewCliente.text = orden.cliente.razonSocial
        } else {
            binding.textViewCliente.text = orden.cliente?.nombre + " " + orden.cliente?.apellidos
        }
        binding.textViewComentario.text = orden.orden.comentario
        // Boton del comentario
        binding.textViewComentario.setOnClickListener {
            val layout = LinearLayout(itemView.context)
            layout.orientation = LinearLayout.VERTICAL
            val comentario = EditText(itemView.context)
            layout.addView(comentario)
            comentario.setText(orden.orden.comentario)
            AlertDialog.Builder(itemView.context)
                .setTitle("Comentario:\n")
                .setCancelable(false)
                .setView(layout)
                .setPositiveButton("Guardar") { _, _ ->
                    orden.orden.comentario = comentario.text.toString()
                    binding.textViewComentario.text = comentario.text.toString()
                    ordenViewModel.modificarOrden(orden.orden)
                }
                .setNegativeButton("Cancelar") { _, _ -> }.show()

        }

        // Recycler y Adaptador de las Direcciones
        var listaDirecciones = orden.direccion.sortedByDescending { it.id }
        listaDirecciones = listaDirecciones.toMutableList()

        val adapterDirecciones = DireccionAdapter(listaDirecciones, ordenViewModel)
        binding.recyclerViewDirecciones.adapter = adapterDirecciones
        binding.recyclerViewDirecciones.layoutManager = LinearLayoutManager(itemView.context)
        binding.recyclerViewDirecciones.itemAnimator = DefaultItemAnimator()

        // Añadir nueva dirección
        binding.textViewDirecciones2.setOnClickListener {
            val layout = LinearLayout(itemView.context)
            layout.orientation = LinearLayout.VERTICAL

            val direccionEditText = EditText(itemView.context)
            direccionEditText.hint = "Direccion..."
            layout.addView(direccionEditText)

            val poblacionEditText = EditText(itemView.context)
            poblacionEditText.hint = "Población..."
            layout.addView(poblacionEditText)

            val codigoPostalEditText = EditText(itemView.context)
            codigoPostalEditText.hint = "Código Postal..."
            codigoPostalEditText.inputType = InputType.TYPE_CLASS_NUMBER
            val filters = arrayOfNulls<InputFilter>(1)
            filters[0] = InputFilter.LengthFilter(5)
            codigoPostalEditText.filters = filters
            layout.addView(codigoPostalEditText)

            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("Modificar:\n")
            builder.setCancelable(false)
            builder.setView(layout)
            builder.setPositiveButton("Guardar") { dialogInterface: DialogInterface?, _: Int ->

                if (poblacionEditText.text.isBlank() || direccionEditText.text.isBlank() || codigoPostalEditText.text.isBlank()) {
                    ordenViewModel.mensajeFlotante.postValue("Debes completar todos los campos.")
                    // todo Que no se cierre esta ventana...
                } else {
                    val nuevaDireccion = Direccion(
                        id = System.currentTimeMillis().toInt(),
                        direccion = direccionEditText.text.toString(),
                        poblacion = poblacionEditText.text.toString(),
                        codigoPostal = codigoPostalEditText.text.toString(),
                        actual = true,
                        clienteId = orden.cliente.id,
                        fechaCreacion = timestamp(),
                        fechaModificacion = timestamp(),
                    )
                    ordenViewModel.nuevaDireccion(nuevaDireccion)
                    listaDirecciones.add(nuevaDireccion)
                    adapterDirecciones.notifyItemInserted(listaDirecciones.size)
                }
            }
            builder.setNegativeButton("Cancelar") { dialogInterface: DialogInterface?, _: Int -> }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }


        // Recycler y Adaptador de los CONTACTOS
        var listaContactos = orden.contacto.sortedByDescending { it.principal == true }
        listaContactos = listaContactos.toMutableList()

        val adapterContactos = ContactoAdapter(listaContactos, ordenViewModel)
        binding.recyclerViewContactos.adapter = adapterContactos
        binding.recyclerViewContactos.layoutManager = LinearLayoutManager(itemView.context)
        binding.recyclerViewContactos.itemAnimator = DefaultItemAnimator()

        // Añadir nuevo contacto
        binding.textViewContactos.setOnClickListener {
            val layout = LinearLayout(itemView.context)
            layout.orientation = LinearLayout.VERTICAL
            val telefono = EditText(itemView.context)
            telefono.hint = "Teléfono"
            val nombre = EditText(itemView.context)
            nombre.hint = "Nombre del contacto"
            layout.addView(telefono)
            layout.addView(nombre)
            AlertDialog.Builder(itemView.context)
                .setTitle("Añadir contacto:\n")
                .setCancelable(false)
                .setView(layout)
                .setPositiveButton("Guardar") { _: DialogInterface?, _: Int ->
                    val nuevoContacto = Contacto(
                        id = System.currentTimeMillis().toInt(),
                        contacto = telefono.text.toString(),
                        tipoContacto = "Telefono",
                        nombre = nombre.text.toString(),
                        principal = false,
                        activo = true,
                        clienteId = orden.cliente.id,
                        fechaCreacion = timestamp(),
                        fechaModificacion = timestamp()
                    )
                    ordenViewModel.nuevoContacto(nuevoContacto)
                    listaContactos.add(nuevoContacto)
                    adapterContactos.notifyItemInserted(listaContactos.size)
                }
                .setNegativeButton(
                    "Cancelar"
                ) { _: DialogInterface?, _: Int -> }.show()

            adapterContactos.notifyItemInserted(listaContactos.size)
        }

        // Recycler y Adaptador de los SERVICIOS
//        var listaServicios = orden.servicioCompleto.sortedByDescending { it.estado.last()?.estado == Sesion.filtroEstado }
        var listaServicios = orden.servicioCompleto.filter { it.estado.last()?.estado == Sesion.filtroEstado }
        listaServicios = listaServicios.toMutableList()

        val adapterServicios = ServicioAdapter(listaServicios,true,ordenViewModel)
        binding.recyclerViewServicios.adapter = adapterServicios
        binding.recyclerViewServicios.layoutManager = LinearLayoutManager(itemView.context)
        binding.recyclerViewServicios.itemAnimator = DefaultItemAnimator()

        // Añadir nuevo Servicio
        binding.textViewServicios.setOnClickListener {
            val layout = LinearLayout(itemView.context)
            layout.orientation = LinearLayout.VERTICAL
            val descripcionPrevia = EditText(itemView.context)
            val tipoPendiente = EditText(itemView.context)
            descripcionPrevia.hint = "Descripción del servicio (ventana, mesa, etc)"
            tipoPendiente.hint = "Motivo del servicio: Precio, futuro parte, etc."
            layout.addView(descripcionPrevia)
            layout.addView(tipoPendiente)

            AlertDialog.Builder(itemView.context)
                .setTitle("Añadir contacto:\n")
                .setCancelable(false)
                .setView(layout)
                .setPositiveButton("Guardar") { _: DialogInterface?, _: Int ->
                    if (descripcionPrevia.text.isEmpty()) {
                        ordenViewModel.mensajeFlotante.postValue("Servicio no guardado. \nTiene que tener una descripción")
                    } else {
                        val estado = Estado(
                            id = System.currentTimeMillis().toInt(),
                            estado = "Medir",
                            creadoPor = Sesion.usuarioId,
                            fechaCreacion = timestamp(),
                            fechaModificacion = timestamp()
                        )

                        val nuevoServicio = Servicio(
                            id = System.currentTimeMillis().toInt(),
                            descripcionPrevia = descripcionPrevia.text.toString(),
                            medido = null,
                            comentarioMedicion = null,
                            fechaMedicion = null,
                            montado = null,
                            comentarioMontaje = null,
                            fechaMontaje = null,
                            pendiente = true,
                            tipoPendiente = tipoPendiente.text.toString(),
                            comentarioPendiente = null,
                            fechaPendiente = null,
                            descripcionUno = null,
                            descripcionDos = null,
                            descripcionTres = null,
                            descripcionCuatro = null,
                            descripcionCinco = null,
                            desperfectos = null,
                            cantidad = null,
                            modeloA = null,
                            camara = null,
                            palilleria = null,
                            modeloB = null,
                            luzAlto = null,
                            luzAncho = null,
                            espatulaAlto = null,
                            espatulaAncho = null,
                            ofertadaAlto = null,
                            ofertadaAncho = null,
                            formuladaAlto = null,
                            formuladaAncho = null,
                            modificadaAlto = null,
                            modificadaAncho = null,
                            sellado = null,
                            manufacturaA = null,
                            manufacturaB = null,
                            observaciones = null,
                            restos = null,
                            recogido = null,
                            fechaCreacion = timestamp(),
                            fechaModificacion = timestamp(),
                            ordenId = orden.orden.id,
                            estadoId = null, // todo Cambiar esto eh!!! -> QUe se cambie en el servidor tambien.
                            faseFormulario = null,
                        )
                        // Para guardarlo en la bbdd
                        val listaEstados = mutableListOf<Estado>()
                        listaEstados.add(estado)

                        val servicioCompleto = ServicioCompleto(
                            nuevoServicio, listaEstados, emptyList(), emptyList(), emptyList(), emptyList(), emptyList()
                        )

                        ordenViewModel.nuevoServicio(servicioCompleto)
                        listaServicios.add(servicioCompleto)
                        adapterServicios.notifyItemInserted(listaServicios.size)
                    }

                }
                .setNegativeButton(
                    "Cancelar"
                ) { _: DialogInterface?, _: Int -> }.show()

        }

//        // Boton de Firma. Si está to do firmado, no saldrá nuevamente. Si faltan firmas, saldrá.
//        var comprobarFirmas = false
//        var palabraBuscar = ""
//        if (Sesion.filtro_estado == "Medir" || Sesion.filtro_estado == "Medido") {
//            palabraBuscar = "Medicion"
//        } else {
//            palabraBuscar = "Instalacion"
//        }
//
//        for (servicio in listaServicios) {
//            for (firma in servicio.firma){
//                if (!firma!!.tipo_firma.equals(palabraBuscar)){
//                    comprobarFirmas = true
//                }
//            }
//
//        }

//        if (comprobarFirmas) {
//            binding.botonFinalizarFirmar.visibility = View.VISIBLE
//            binding.botonFinalizarFirmar.setOnClickListener {
//                val intent = Intent(itemView.context, FirmaActivity::class.java)
//                intent.putExtra("orden_id", orden.orden!!.id)
//                itemView.context.startActivity(intent)
//            }
//        } else {
//            binding.botonFinalizarFirmar.visibility = View.GONE
//        }

    }

}