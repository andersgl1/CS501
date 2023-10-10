package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<TextView>(R.id.text)
        val buttons = listOf(R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.buttonDiv, R.id.buttonTimes, R.id.buttonDot, R.id.buttonEquals, R.id.buttonPlus, R.id.buttonMinus, R.id.buttonSqrt)

        for (button in buttons) {
            val current = findViewById<Button>(button)

            current.setOnClickListener {
                val toCalc = view.text.toString()
                if (view.text != "0.0") view.text = when (current.text) {
                    "=" -> calculateMDAS(convertSqrt(toCalc)).toString()
                    else -> view.text.toString().plus(current.text.toString())
                } else view.text = current.text
            }
        }

        view.setOnClickListener {
            view.text = "0.0"
        }

    }
    
    private fun convertSqrt(x: String): String {
        if (!x.contains("sqrt")) return x

        var result = x
        val sqrtIntances = x.split("sqrt")
        val subDivRes = sqrt(sqrtIntances[1].split('+', '-', '*', '/')[0].toDouble())

        result = result.replaceFirst("sqrt${sqrtIntances[1]}", subDivRes.toString())
        result = convertSqrt(result)

        return result
    }


    private fun calculateMDAS(x: String): Double {
        val numbers = x.split("[+\\-*/]".toRegex())
        val operators = x.split("\\d+(?:\\.\\d+)?".toRegex()).filter { it.isNotEmpty() }
        var result = numbers[0].toDouble()
        var i = 1

        while (i < numbers.size) {
            when (operators[i - 1]) {
                "*" -> result *= numbers[i].toDouble()
                "/" -> result /= numbers[i].toDouble()
                "+" -> {
                    var j = i + 1
                    var tempResult = numbers[i].toDouble()

                    while (j < numbers.size && (operators[j - 1] == "+" || operators[j - 1] == "-")) {
                        when (operators[j - 1]) {
                            "+" -> tempResult += numbers[j].toDouble()
                            "-" -> tempResult -= numbers[j].toDouble()
                        }
                        j++
                    }
                    result += tempResult
                    i = j - 1
                }
                "-" -> {
                    var j = i + 1
                    var tempResult = -numbers[i].toDouble()

                    while (j < numbers.size && (operators[j - 1] == "+" || operators[j - 1] == "-")) {
                        when (operators[j - 1]) {
                            "+" -> tempResult += numbers[j].toDouble()
                            "-" -> tempResult -= numbers[j].toDouble()
                        }
                        j++
                    }
                    result += tempResult
                    i = j - 1
                }
            }
            i++
        }

        return "%.3f".format(result).toDouble()
    }
}