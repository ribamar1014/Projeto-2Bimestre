package com.example.projeto2segundbim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.projeto2segundbim.models.InformaModelo
import java.util.ArrayList
class MainActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var dispList: ArrayList<InformaModelo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnon: Button = findViewById(R.id.button)
        btnon.setOnClickListener{
            dbRef = FirebaseDatabase.getInstance().getReference("DadosUsuario")

            val docefav = findViewById<EditText>(R.id.dcfav).text.toString()
            val salgafav = findViewById<EditText>(R.id.slgfav).text.toString()
            val filmefav = findViewById<EditText>(R.id.flmfav).text.toString()
            val musifav = findViewById<EditText>(R.id.mscfav).text.toString()

            if (docefav.isEmpty()){
                findViewById<EditText>(R.id.dcfav).error ="Insira a sua Comida Favorita pfv :)"
            }
            if (salgafav.isEmpty()){
                findViewById<EditText>(R.id.slgfav).error ="Insira seu filme Favorito pfv :)"
            }
            if (filmefav.isEmpty()){
                findViewById<EditText>(R.id.flmfav).error ="Insira sua série Favorita pfv :)"
            }
            if (musifav.isEmpty()){
                findViewById<EditText>(R.id.mscfav).error ="Insira sua música Favorita pfv :)"
            }

            val dispId = dbRef.push().key?: ""


            val dispositivosss = InformaModelo(dispId,docefav,salgafav,musifav,filmefav)

            dbRef.child(dispId).setValue(dispositivosss)
                .addOnCompleteListener{
                    Toast.makeText(this,"Dado inserido com sucesso", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener{ err->
                    Toast.makeText(this,"Erro ${err.message}", Toast.LENGTH_SHORT).show()
                }



            val intent = Intent(this@MainActivity, botoes::class.java)
            startActivity(intent)

        }

    }
}








