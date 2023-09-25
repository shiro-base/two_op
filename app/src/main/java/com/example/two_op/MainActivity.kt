package com.example.two_op

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var num1EditText: EditText
    private lateinit var num2EditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var operationRadioGroup: RadioGroup
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Calculator"

        num1EditText = findViewById(R.id.num1EditText)
        num2EditText = findViewById(R.id.num2EditText)
        resultTextView = findViewById(R.id.resultTextView)
        operationRadioGroup = findViewById(R.id.operationRadioGroup)
        calculateButton = findViewById(R.id.calculateButton)

        calculateButton.setOnClickListener {
            calculateResult()
        }
    }

    private fun calculateResult() {
        val num1Str = num1EditText.text.toString()
        val num2Str = num2EditText.text.toString()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            showToast("Please enter both numbers.")
            resultTextView.text = ""
            return
        }

        val num1 = num1Str.toDouble()
        val num2 = num2Str.toDouble()
        val selectedRadioButtonId = operationRadioGroup.checkedRadioButtonId

        if (selectedRadioButtonId == -1) {
            showToast("Please select an operation.")
            return
        }

        val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)

        val result = when (selectedRadioButton.text.toString()) {
            "Add" -> num1 + num2
            "Subtract" -> num1 - num2
            "Multiply" -> num1 * num2
            "Divide" -> {
                if (num2 == 0.0) {
                    showToast("Cannot divide by zero.")
                    return
                }
                num1 / num2
            }
            "Modulus" -> {
                if (num2 == 0.0) {
                    showToast("Cannot perform modulus with zero as the divisor.")
                    return
                }
                num1 % num2
            }
            else -> {
                showToast("Invalid operation.")
                return
            }
        }
        resultTextView.visibility = View.VISIBLE
        resultTextView.text = "Result: $result"
    }

    private fun showToast(message: String) {
        resultTextView.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}





