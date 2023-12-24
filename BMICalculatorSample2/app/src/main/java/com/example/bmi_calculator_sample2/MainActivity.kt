package com.example.bmi_calculator_sample2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.tvHealthy.text = healthyMessage(bmi)
    }

    private fun healthyMessage(bmi: Double): String {
        return when {
            bmi < 18.5 -> {
                showHealthMessage("Underweight")
            }
            bmi < 25.0 -> {
                showHealthMessage("Health")
            }
            bmi < 30.0 -> {
                showHealthMessage("Overweight")
            }
            else -> {
                showHealthMessage("obese")
            }
        }
    }

    private fun showHealthMessage(healthyMessage: String): String {
        return String.format("Considered: %s", healthyMessage)
    }
}