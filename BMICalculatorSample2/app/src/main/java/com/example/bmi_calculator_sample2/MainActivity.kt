package com.example.bmi_calculator_sample2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.bmi_calculator_sample2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.weightPicker.minValue = 30
        binding.weightPicker.maxValue = 300

        binding.heightPicker.minValue = 100
        binding.heightPicker.maxValue = 250

        binding.weightPicker.setOnValueChangedListener{_,_,_ ->
            calculateBMI()
        }

        binding.heightPicker.setOnValueChangedListener{_,_,_ ->
            calculateBMI()
        }

    }

    private fun calculateBMI() {
        val height = binding.heightPicker.value
        val doubleHeight = height.toDouble()/100

        val weight = binding.weightPicker.value

        val bmi = weight.toDouble() / (doubleHeight * doubleHeight)

        binding.tvResult.text = String.format("Your BMI is: %2f", bmi)

        healthyMessage(bmi)
    }

    private fun healthyMessage(bmi: Double) {

        var messageText = ""
        var color = 0

        when {
            bmi < 18.5 -> {
                messageText = showHealthMessage("Underweight")
                color = R.color.under_weight
            }
            bmi < 25.0 -> {
                messageText = showHealthMessage("Normal weight")
                color = R.color.normal_weight
            }
            bmi < 30.0 -> {
                messageText = showHealthMessage("Overweight")
                color = R.color.over_weight
            }
            else -> {
                messageText = showHealthMessage("obese")
                color = R.color.obese
            }
        }

        binding.tvHealthy.text = messageText
        binding.tvHealthy.setTextColor(ContextCompat.getColor(this, color))
    }

    private fun showHealthMessage(healthyMessage: String): String {

        return String.format("Considered: %s", healthyMessage)
    }
}