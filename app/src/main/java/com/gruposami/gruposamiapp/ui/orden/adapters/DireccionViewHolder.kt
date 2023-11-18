package com.gruposami.gruposamiapp.ui.orden.adapters

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.gruposami.gruposamiapp.databinding.ItemDireccionBinding
import com.gruposami.gruposamiapp.domain.direccion.model.Direccion
import com.gruposami.gruposamiapp.ui.orden.OrdenViewModel


class DireccionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDireccionBinding.bind(view)

    fun render(item: Direccion, ordenViewModel: OrdenViewModel) {
        binding.textViewDireccion.text = item.direccion
        binding.textviewPoblacion.text = item.poblacion
        binding.textViewCodigoPostal.text = item.codigoPostal

        // Boton para ir al maps y poner directamente la dirección
        binding.botonGPS.setOnClickListener {
            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("¿Abrir en google maps?")
            builder.setPositiveButton("Si") { _, _ ->
                val map = "http://maps.google.co.in/maps?q=${item.direccion},%20${item.codigoPostal},%20${item.poblacion}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(map))
                itemView.context.startActivity(intent)
            }
            builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }
            builder.show()
        }

        // Si deja presionado el cardview, que salga un menu de opciones.
        binding.cardViewDireccion.setOnLongClickListener{
            val charSequences = arrayOf<CharSequence>("Modificar")
            val builder: AlertDialog.Builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("Opciones")
            builder.setItems(charSequences) { _, option ->

                // Cambiar datos del contacto actual
                if (option == 0) {
                    val layout = LinearLayout(itemView.context)
                    layout.orientation = LinearLayout.VERTICAL

                    val direccionEditText = EditText(itemView.context)
                    direccionEditText.hint = "Direccion..."
                    direccionEditText.setText(item.direccion.toString())
                    layout.addView(direccionEditText)

                    val poblacionEditText = EditText(itemView.context)
                    poblacionEditText.hint = "Población..."
                    poblacionEditText.setText(item.poblacion.toString())
                    layout.addView(poblacionEditText)

                    val codigoPostalEditText = EditText(itemView.context)
                    codigoPostalEditText.hint = "Población..."
                    codigoPostalEditText.inputType = InputType.TYPE_CLASS_NUMBER
                    val filters = arrayOfNulls<InputFilter>(1)
                    filters[0] = LengthFilter(5)
                    codigoPostalEditText.filters = filters

                    codigoPostalEditText.setText(item.codigoPostal.toString())
                    layout.addView(codigoPostalEditText)

                    AlertDialog.Builder(itemView.context)
                        .setTitle("Modificar:\n")
                        .setCancelable(true)
                        .setView(layout)
                        .setPositiveButton("Guardar") { _: DialogInterface?, _: Int ->
                            item.direccion = direccionEditText.text.toString()
                            item.poblacion = poblacionEditText.text.toString()
                            item.codigoPostal = codigoPostalEditText.text.toString()
                            ordenViewModel.modificarDireccion(item)

                            binding.textViewDireccion.text = item.direccion
                            binding.textviewPoblacion.text = item.poblacion
                            binding.textViewCodigoPostal.text = item.codigoPostal

                        }
                        .setNegativeButton("Cancelar") { _: DialogInterface?, _: Int -> }
                        .show()
                }

            }
            builder.show()
            return@setOnLongClickListener true
        }

    }

}