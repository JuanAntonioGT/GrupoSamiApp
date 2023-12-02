package com.gruposami.gruposamiapp.ui.formulario.seccion

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.gruposami.gruposamiapp.databinding.Formulario11Binding
import com.gruposami.gruposamiapp.domain.estado.model.Estado
import com.gruposami.gruposamiapp.domain.servicio.model.Servicio
import com.gruposami.gruposamiapp.domain.sesion.model.Sesion
import com.gruposami.gruposamiapp.ui.formulario.FormularioActivity
import com.gruposami.gruposamiapp.utils.timestamp


// 14  METERLE IMAGENES Y MAS COSILLAS NO :S???
fun resumenMedicion(activity: FormularioActivity, servicio: Servicio) {
    val binding: Formulario11Binding = Formulario11Binding.inflate(activity.layoutInflater)
    val view: View = binding.root
    activity.setContentView(view)

    servicio.faseFormulario = 14
    activity.formularioViewModel.modificarServicio(servicio)

    val textView1: TextView = binding.textView
    val textView2: TextView = binding.textView2
    val textView3: TextView = binding.textView3
    val textView4: TextView = binding.textView4
    val textView5: TextView = binding.textView5
    val textView6: TextView = binding.textView6
    val textView7: TextView = binding.textView7
    val botonAtras: Button = binding.botonAtras
    val botonFinalizarMedicion: Button = binding.botonFinalizarMedicion
    if (!servicio.descripcionUno.isNullOrEmpty()) {
        textView1.append(servicio.descripcionUno + "\n")
    }
    if (!servicio.descripcionDos.isNullOrEmpty()) {
        textView1.append(servicio.descripcionDos + "\n")
    }
    if (!servicio.descripcionTres.isNullOrEmpty()) {
        textView1.append(servicio.descripcionTres + "\n")
    }
    if (!servicio.descripcionCuatro.isNullOrEmpty()) {
        textView1.append(servicio.descripcionCuatro + "\n")
    }
    if (!servicio.descripcionCinco.isNullOrEmpty()) {
        textView1.append(servicio.descripcionCinco + "\n")
    }
    if (!servicio.desperfectos.isNullOrEmpty()) {
        textView2.append("-Desperfectos: ${servicio.desperfectos}\n")
    }
    if (servicio.cantidad != null) {
        textView3.append(
            """
                -Cantidad: ${servicio.cantidad.toString()}

                """.trimIndent()
        )
    }
    if (!servicio.modeloA.isNullOrEmpty()) {
        textView3.append(
            """
                -Modelo: ${servicio.modeloA.toString()}

                """.trimIndent()
        )
    }
    if (!servicio.camara.isNullOrEmpty()) {
        textView3.append(
            """
                -Camara: ${servicio.camara.toString()}

                """.trimIndent()
        )
    }
    if (!servicio.palilleria.isNullOrEmpty()) {
        textView3.append(
            """
                -Palillería: ${servicio.palilleria.toString()}

                """.trimIndent()
        )
    }
    if (!servicio.modeloB.isNullOrEmpty()) {
        textView3.append(
            """
                -Modelo: ${servicio.modeloB.toString()}

                """.trimIndent()
        )
    }
    if (servicio.luzAlto != null) {
        textView4.append(
            """
                -Medida de Alto de Luz: ${servicio.luzAlto.toString()}

                """.trimIndent()
        )
    }
    if (servicio.luzAncho != null) {
        textView4.append(
            """
                -Medida de Ancho de Luz: ${servicio.luzAncho.toString()}

                """.trimIndent()
        )
    }
    if (servicio.espatulaAlto != null) {
        textView4.append(
            """
                -Espatula Alto: ${servicio.espatulaAlto.toString()}

                """.trimIndent()
        )
    }
    if (servicio.espatulaAncho != null) {
        textView4.append(
            """
                -Espatula Ancho: ${servicio.espatulaAncho.toString()}

                """.trimIndent()
        )
    }
    if (servicio.ofertadaAlto != null) {
        textView4.append(
            """
                -Medida Ofertada de Alto: ${servicio.ofertadaAlto.toString()}

                """.trimIndent()
        )
    }
    if (servicio.ofertadaAncho != null) {
        textView4.append(
            """
                -Medida Ofertada de Ancho: ${servicio.ofertadaAncho.toString()}

                """.trimIndent()
        )
    }
    if (servicio.formuladaAlto != null) {
        textView4.append(
            """
                -Medida según Fórmula Alto: ${servicio.formuladaAlto.toString()}

                """.trimIndent()
        )
    }
    if (servicio.formuladaAncho != null) {
        textView4.append(
            """
                -Medida según Fórmula Ancho: ${servicio.formuladaAncho.toString()}

                """.trimIndent()
        )
    }
    if (servicio.modificadaAlto != null) {
        textView4.append(
            """
                -Medida Modificada de Alto: ${servicio.modificadaAlto.toString()}

                """.trimIndent()
        )
    }
    if (servicio.modificadaAncho != null) {
        textView4.append(
            """
                -Medida Modificada de Ancho: ${servicio.modificadaAncho.toString()}

                """.trimIndent()
        )
    }
    if (!servicio.sellado.isNullOrEmpty()) {
        textView5.append("-Sellado: ${servicio.sellado}\n")
    }
    if (!servicio.manufacturaA.isNullOrEmpty()) {
        textView6.append(
            """
                -Manufactura: ${servicio.manufacturaA.toString()}

                """.trimIndent()
        )
    }
    if (!servicio.observaciones.isNullOrEmpty()) {
        textView7.append(
            """
                -Observaciones de la Instalacion del Cristal: ${servicio.observaciones.toString()}

                """.trimIndent()
        )
    }
    if (servicio.restos != null) {
        if (servicio.restos == false) {
            textView7.append(
                """
                -Guarda Resto: No

                """.trimIndent()
            )
        } else {
            textView7.append(
                """
                -Guarda Resto: Si

                """.trimIndent()
            )
        }
    }
    if (servicio.recogido != null) {
        if (servicio.recogido == false) {
            textView7.append(
                """
                -Recogido: No

                """.trimIndent()
            )
        } else {
            textView7.append(
                """
                -Recogido: Si

                """.trimIndent()
            )
        }
    }
    botonAtras.setOnClickListener {
        if (Sesion.filtroEstado == "Medir") {
            servicio.faseFormulario = 13
        } else {
            servicio.faseFormulario = 7
        }
        activity.ubicacion()
    }
    botonFinalizarMedicion.setOnClickListener {
        if (Sesion.filtroEstado == "Medir") {
            servicio.medido = true
            servicio.fechaMedicion = timestamp()

            val estado = Estado(
                id = System.currentTimeMillis().toInt(),
                fechaCreacion = timestamp(),
                fechaModificacion = timestamp(),
                estado = "Medido",
                creadoPor = Sesion.usuarioId.toInt()
            )
            servicio.estadoId = estado.id
            activity.formularioViewModel.insertarEstadoSer(estado, servicio.id)
            activity.formularioViewModel.modificarServicio(servicio)

        } else {
            servicio.montado = true
            servicio.fechaMontaje = timestamp()

            val estado = Estado(
                id = System.currentTimeMillis().toInt(),
                fechaCreacion = timestamp(),
                fechaModificacion = timestamp(),
                estado = "Montado",
                creadoPor = Sesion.usuarioId.toInt()
            )
            servicio.estadoId = estado.id
            activity.formularioViewModel.insertarEstadoSer(estado, servicio.id)
            activity.formularioViewModel.modificarServicio(servicio)

        }
        // Añadir que lo ha medido X persona/s
        servicio.faseFormulario = 15
        activity.ubicacion()
    }
}