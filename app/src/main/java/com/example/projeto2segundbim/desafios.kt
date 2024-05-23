package com.example.projeto2segundbim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class desafios : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.desadi)

        val generator = UniqueRandomGenerator()
        val x = generator.generateUniqueNumber(1, 5)

        if (x == 1) {
            val desafios = findViewById<TextView>(R.id.tvmostrar)
            desafios.text =
                "Noite de Fondue Romântico:\n"+
            "Desafio: Organize uma noite de fondue romântico.\n"+
            "Como: Prepare um fondue de queijo e um fondue de chocolate, acompanhados de uma variedade de pães, frutas, legumes e doces para mergulhar. Assista a filmes românticos juntos enquanto desfrutam do fondue."
        }

        if (x == 2) {
            val desafios = findViewById<TextView>(R.id.tvmostrar)
            desafios.text =
                "Jantar à Luz de Velas:\n" +
                        "Desafio: Prepare um jantar romântico à luz de velas em casa.\n" +
                        "Como: Prepare uma refeição caseira especial, como massa caseira ou um prato gourmet favorito do seu parceiro. Decore a mesa com velas, flores frescas e música romântica para criar uma atmosfera íntima.\n"
        }

        if (x == 3) {
            val desafios = findViewById<TextView>(R.id.tvmostrar)
            desafios.text =
                "CPiquenique ao Pôr do Sol:\n" +
                        "Desafio: Organize um piquenique romântico ao pôr do sol.\n" +
                        "Como: Prepare uma cesta de piquenique com uma seleção de queijos, salgados, frutas, chocolates e uma garrafa de vinho. Escolha um local bonito, como um parque ou uma praia, e assistam juntos ao pôr do sol enquanto compartilham o piquenique."
        }


        val btnon: Button = findViewById(R.id.btndica)
        btnon.setOnClickListener{
            val intent = Intent(this@desafios, Dica::class.java)
            startActivity(intent)

        }
        val btnone: Button = findViewById(R.id.enviar)
        btnone.setOnClickListener{
            val intent = Intent(this@desafios, avaliacao::class.java)
            startActivity(intent)

        }


    }



    class UniqueRandomGenerator {
        private val generatedNumbers = mutableListOf<Int>()

        fun generateUniqueNumber(min: Int, max: Int): Int {
            var randomNumber: Int
            do {
                randomNumber = Random.nextInt(min, max + 1)
            } while (generatedNumbers.contains(randomNumber))

            generatedNumbers.add(randomNumber)
            return randomNumber
        }
    }
}




