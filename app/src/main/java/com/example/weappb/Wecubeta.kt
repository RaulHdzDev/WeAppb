package com.example.weappb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.widget.TextView
import kotlin.math.round


class Wecubeta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wecubeta)

        val btnCalcularCubeta = findViewById<Button>(R.id.btnCalcularCubeta)
        btnCalcularCubeta.setOnClickListener(View.OnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference()
            var lecturaPorcentaje:String = ""
            var lecturaPorcentajeInt:Float = 0.0F
            var lecturaVolumenInt:Float = 0.0f
            var lecturaVolumen:String = ""

            ref.child("porcentaje").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snap in dataSnapshot.children){
                        lecturaPorcentaje = snap.value.toString()

                        lecturaPorcentajeInt = lecturaPorcentaje.toFloat()
                        lecturaPorcentajeInt = round(lecturaPorcentajeInt*100)/100
                        lecturaPorcentaje = lecturaPorcentajeInt.toString() + "%"

                        var tvCubetaPorcentaje = findViewById<TextView>(R.id.tvCubetaPorcentaje)
                        tvCubetaPorcentaje.text = lecturaPorcentaje

                        var tvCubetaObservaciones = findViewById<TextView>(R.id.tvCubetaObservaciones)
                        tvCubetaObservaciones.text = "Observaciones"

                        var observacion:String = ""

                        when{

                            lecturaPorcentajeInt <=10 -> observacion = "URGENTE, tu cubeta tiene $lecturaPorcentaje% de agua, ¡rellenalo ahora mismo!"
                            lecturaPorcentajeInt <= 25 && lecturaPorcentajeInt > 10 -> observacion = "Tu cubeta tiene $lecturaPorcentaje de agua, está pronto a vaciarse, ¡rellenalo para no quedarte sin abastecimiento de agua!"
                            lecturaPorcentajeInt <= 50 && lecturaPorcentajeInt > 25 -> observacion = "Tu cubeta tiene $lecturaPorcentaje de agua, ¡revisa constantemente para rellenarlo!"
                            lecturaPorcentajeInt <= 75 && lecturaPorcentajeInt > 50 -> observacion = "Tu cubeta tiene $lecturaPorcentaje de agua, ¡no olvides rellenarlo!"
                            lecturaPorcentajeInt <= 100 && lecturaPorcentajeInt > 75 -> observacion = "Tu cubeta tiene $lecturaPorcentaje de agua, se encuentra con suficiente agua, ¡Bien hecho!"

                        }

                        tvCubetaObservaciones.append("\n"+observacion)

                    }
                    /*lecturaPorcentajeInt = lecturaPorcentaje.toFloat()
                    lecturaPorcentajeInt = round(lecturaPorcentajeInt*100)/100
                    lecturaPorcentaje = lecturaPorcentajeInt.toString()*/
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })

            ref.child("volumen").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (snap in dataSnapshot.children){
                        lecturaVolumen = snap.value.toString()

                        lecturaVolumenInt = lecturaVolumen.toFloat()
                        lecturaVolumenInt = round(lecturaVolumenInt*100)/100
                        lecturaVolumen = lecturaVolumenInt.toString() + "lts"

                        var tvCubetaVolumen = findViewById<TextView>(R.id.tvCubetaVolumen)
                        tvCubetaVolumen.text = lecturaVolumen

                        Log.e("hola",snap.value.toString())
                    }
                    /*lecturaVolumenInt = lecturaVolumen.toFloat()
                    lecturaVolumenInt = round(lecturaVolumenInt*100)/100
                    lecturaVolumen = lecturaVolumenInt.toString()*/
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })

            /*var tvCubetaVolumen = findViewById<TextView>(R.id.tvCubetaVolumen)
            tvCubetaVolumen.text = lecturaVolumen*/

            /*var tvCubetaPorcentaje = findViewById<TextView>(R.id.tvCubetaPorcentaje)
            tvCubetaPorcentaje.text = lecturaPorcentaje*/

            /*var tvCubetaObservaciones = findViewById<TextView>(R.id.tvCubetaObservaciones)
            tvCubetaObservaciones.text = "Observaciones"

            var observacion:String = ""

            when{

                lecturaPorcentajeInt <=10 -> observacion = "URGENTE, tu cubeta tiene $lecturaPorcentaje% de agua, ¡rellenalo ahora mismo!"
                lecturaPorcentajeInt <= 25 && lecturaPorcentajeInt > 10 -> observacion = "Tu cubeta tiene $lecturaPorcentaje% de agua, está pronto a vaciarse, ¡rellenalo para no quedarte sin abastecimiento de agua!"
                lecturaPorcentajeInt <= 50 && lecturaPorcentajeInt > 25 -> observacion = "Tu cubeta tiene $lecturaPorcentaje% de agua, ¡revisa constantemente para rellenarlo!"
                lecturaPorcentajeInt <= 75 && lecturaPorcentajeInt > 50 -> observacion = "Tu cubeta tiene $lecturaPorcentaje% de agua, ¡no olvides rellenarlo!"
                lecturaPorcentajeInt <= 100 && lecturaPorcentajeInt > 75 -> observacion = "Tu cubeta tiene $lecturaPorcentaje% de agua, se encuentra con suficiente agua, ¡Bien hecho!"

            }

            tvCubetaObservaciones.append(observacion)*/

        })

    }
}
