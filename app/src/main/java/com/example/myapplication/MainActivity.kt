package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Button
import android.widget.TextView
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstField = findViewById<EditText>(R.id.first)
        val secondField = findViewById<EditText>(R.id.second)
        val submit = findViewById<Button>(R.id.submit)
        val disp = findViewById<TextView>(R.id.display)
        val groupButtons = listOf(R.id.plus, R.id.minus, R.id.div, R.id.times, R.id.modulo)

        var result = 0.0

        submit.setOnClickListener{
            for (button in groupButtons) {
                val current = findViewById<RadioButton>(button)
                if (current.isChecked) {
                    val firstFieldDouble = firstField.text.toString().toDouble()
                    val secondFieldDouble = secondField.text.toString().toDouble()
                    result = doCalc(current.text.toString(), firstFieldDouble, secondFieldDouble)
                }
            }

            disp.text = result.toString()
        }
    }

    private fun doCalc(x: String, j: Double, z: Double): Double {
        var res = 0.0

        res = when (x) {
            "+" -> j + z
            "-" -> j - z
            "*" -> j * z
            "/" -> j / z
            "%" -> j % z
            else -> 0.0
        }

        return res
    }
}