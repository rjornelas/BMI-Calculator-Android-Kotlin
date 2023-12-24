package com.example.bmi_calculator_sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightValue = findViewById<EditText>(R.id.edtWeight)
        val heightValue = findViewById<EditText>(R.id.edtHeight)
        val btnCalculator = findViewById<Button>(R.id.btnCalculate)

        btnCalculator.setOnClickListener{
            val weightText = weightValue.text.toString()
            val heightText = heightValue.text.toString()

            if(validateInput(weightText, heightText)){
                val bmi = calculateBmi(weightText, heightText)
                val bmiFormatted = formatBmiValue(bmi)
                displayResult(bmiFormatted)
            }
        }
    }

    private fun validateInput(weightValue: String?, heightValue: String?): Boolean {
        return when{
            weightValue.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            heightValue.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmiFormatted: Float) {
        val resultValue = findViewById<TextView>(R.id.tvResultValue)
        val resultLabel = findViewById<TextView>(R.id.tvResultLabel)

        resultValue.text = bmiFormatted.toString()

        var resultText = ""
        var color = 0

        when{
            bmiFormatted < 18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmiFormatted in 18.50..24.99 -> {
            resultText = "Normal weight"
            color = R.color.normal_weight
            }
            bmiFormatted in 25.00..29.99 -> {
                resultText = "over_weight"
                color = R.color.over_weight
            }
            bmiFormatted >29.99 -> {
                resultText = "obese"
                color = R.color.obese
            }
        }

        resultLabel.setTextColor(ContextCompat.getColor(this, color))
        resultLabel.text = resultText

    }

    private fun calculateBmi(weightValue: String, heightValue: String): Float {
        return weightValue.toFloat() / ((heightValue.toFloat() / 100) * (heightValue.toString().toFloat() / 100))
    }
    private fun formatBmiValue(bmi: Float): Float {
       return String.format("%.2f", bmi).toFloat()
    }
}