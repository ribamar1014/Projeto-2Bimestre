package com.example.projeto2segundbim

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto2segundbim.models.InformaModelo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class Dica: AppCompatActivity() {

    private lateinit var tvDoce: TextView
    private lateinit var tvSalgado: TextView
    private lateinit var tvFilme: TextView
    private lateinit var tvMusica: TextView


    private lateinit var dbRef: DatabaseReference
    private var dispList: ArrayList<InformaModelo> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dica)

        tvDoce = findViewById(R.id.textView3)
        tvFilme = findViewById(R.id.textView4)
        tvSalgado = findViewById(R.id.textView5)
        tvMusica = findViewById(R.id.textView6)

        dbRef = FirebaseDatabase.getInstance().getReference("DadosUsuario")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dispList.clear()
                if (snapshot.exists()) {
                    for (favSnap in snapshot.children) {
                        val favData = favSnap.getValue(InformaModelo::class.java)
                        dispList.add(favData!!)
                    }

                    if (dispList.isNotEmpty()) {
                        tvDoce.text = dispList[0].doce.toString()
                        tvFilme.text = dispList[0].salgado.toString()
                        tvSalgado.text = dispList[0].musica.toString()
                        tvMusica.text = dispList[0].filme.toString()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled event
            }
        })
    }
}



