package com.gruposami.gruposamiapp.ui.orden.adapters

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.gruposami.gruposamiapp.databinding.ItemContactoBinding
import com.gruposami.gruposamiapp.domain.contacto.model.Contacto
import com.gruposami.gruposamiapp.ui.orden.OrdenViewModel


class ContactoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemContactoBinding.bind(view)

    fun render(contacto: Contacto, ordenViewModel: OrdenViewModel) {


        // Formato para el número de telefono y nombre
        if (contacto.nombre.isNullOrBlank()) {
            binding.rowTelefonosTextView.text = "${contacto.contacto}"
        } else {
            binding.rowTelefonosTextView.text = "${contacto.contacto} - ${contacto.nombre.toString()}"
        }

        if (contacto.principal == true){
            binding.rowTelefonosImageButton.setColorFilter(Color.BLACK)
        }

        // Botón verde para llamar por telefono con sus permisos y tal
        binding.rowTelefonosImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + contacto.contacto?.substring(0, 9))
            if (ActivityCompat.checkSelfPermission(
                    itemView.context,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    itemView.context,
                    "No tienes permisos para llamar",
                    Toast.LENGTH_SHORT
                ).show()
                ActivityCompat.requestPermissions(
                    (itemView.context as Activity),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                itemView.context.startActivity(intent)
            }
        }

        // Al pulsar de manera prolongada aparecerá un menú para MODIFICAR o ELIMINAR
        binding.rowTelefonosTextView.setOnLongClickListener {
            val charSequences = arrayOf<CharSequence>("Modificar", "Marcar como principal")
            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(itemView.context)
            builder.setTitle("Contacto")
            builder.setItems(charSequences) { _, selected_option ->

                // Cambiar datos del contacto actual
                if (selected_option == 0) {
                    val layout = LinearLayout(itemView.context)
                    layout.orientation = LinearLayout.VERTICAL
                    val contactoEditText = EditText(itemView.context)
                    contactoEditText.hint = "Telefono"
                    contactoEditText.setText(contacto.contacto.toString())
                    layout.addView(contactoEditText)
                    val nombreEditText = EditText(itemView.context)
                    nombreEditText.hint = "Nombre del contacto"
                    nombreEditText.setText(contacto.nombre.toString())
                    layout.addView(nombreEditText)

                    AlertDialog.Builder(itemView.context)
                        .setTitle("Modificar:\n")
                        .setCancelable(true)
                        .setView(layout)
                        .setPositiveButton("Guardar") { _: DialogInterface?, _: Int ->
                            contacto.contacto = contactoEditText.text.toString()
                            contacto.nombre = nombreEditText.text.toString()
                            ordenViewModel.modificarContacto(contacto)

                            if (contacto.nombre.isNullOrBlank()) {
                                binding.rowTelefonosTextView.text = "${contacto.contacto}"
                            } else {
                                binding.rowTelefonosTextView.text = "${contacto.contacto} - ${contacto.nombre.toString()}"
                            }

                        }
                        .setNegativeButton("Cancelar") { _: DialogInterface?, _: Int -> }
                        .show()
                }

                // Contacto Principal
                if (selected_option == 1) {
                    AlertDialog.Builder(itemView.context)
                        .setTitle("¿Es el contacto principal?\n")
                        .setCancelable(true)
                        .setPositiveButton("Si") { _: DialogInterface?, _: Int ->
                            contacto.principal = true
                            ordenViewModel.modificarContacto(contacto)
                            binding.rowTelefonosImageButton.setColorFilter(Color.BLACK)
                        }
                        .setNegativeButton("No") { _: DialogInterface?, _: Int ->
                            contacto.principal = false
                            ordenViewModel.modificarContacto(contacto)
                            binding.rowTelefonosImageButton.colorFilter = null
                        }.show()
                }
            }
            builder.show()
            return@setOnLongClickListener true
        }
    }
}