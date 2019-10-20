package com.example.weappb


//Putomelapelas

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Iniciosesion : AppCompatActivity(){

    private lateinit var txtUser:EditText
    private lateinit var txtPass:EditText
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciosesion)

        txtUser=findViewById(R.id.txtUser)
        txtPass=findViewById(R.id.txtPass)
        auth=FirebaseAuth.getInstance()
    }

    fun clickRegistro(view: View){
        startActivity(Intent(this, Registro::class.java))
    }

    fun clickRecuperarContraseña(view: View){
        startActivity(Intent(this, Recuperar::class.java))
    }

    fun login(view: View){
        loginUser()
    }

    private fun loginUser(){
        val user:String=txtUser.text.toString()
        val pass:String=txtPass.text.toString()

        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)){
            auth?.signInWithEmailAndPassword(user,pass)
                ?.addOnCompleteListener(this){
                    task ->

                    if (task.isSuccessful){
                        accion()
                    }
                    else{
                        Toast.makeText(this,"Error en el inicio de sesión",Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun accion(){
        startActivity(Intent(this,Weappeate::class.java))
    }

}
