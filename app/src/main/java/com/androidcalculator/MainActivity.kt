package com.androidcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberButtons = listOf<Button>(
            findViewById<Button>(R.id.decimalButton),
            findViewById<Button>(R.id.zeroButton),
            findViewById<Button>(R.id.oneButton),
            findViewById<Button>(R.id.twoButton),
            findViewById<Button>(R.id.threeButton),
            findViewById<Button>(R.id.fourButton),
            findViewById<Button>(R.id.fiveButton),
            findViewById<Button>(R.id.sixButton),
            findViewById<Button>(R.id.sevenButton),
            findViewById<Button>(R.id.eightButton),
            findViewById<Button>(R.id.nineButton)
        )
        val operationButtons = listOf<Button>(
            findViewById<Button>(R.id.modButton),
            findViewById<Button>(R.id.divideButton),
            findViewById<Button>(R.id.multiplyButton),
            findViewById<Button>(R.id.minusButton),
            findViewById<Button>(R.id.plusButton)
        )
        val clearButton = findViewById<Button>(R.id.clearButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val equalsButton = findViewById<Button>(R.id.equalsButton)
        val negativeButton = findViewById<Button>(R.id.negativeButton)
        val previousOperandText = findViewById<TextView>(R.id.previousOperationText)
        val currentOperandText = findViewById<TextView>(R.id.currentOperationText)
        var previousOperand = ""
        var currentOperand = ""
        var operator = ""

        numberButtons.forEach { button: Button ->
            button.setOnClickListener {
                currentOperandText.append(button.text)
                currentOperand += button.text.toString()
            }
        }
        operationButtons.forEach { button: Button ->
            button.setOnClickListener {
                operator = button.text.toString()
                previousOperand = currentOperandText.text.toString()
                previousOperandText.text = currentOperandText.text.toString()
                previousOperandText.append(operator)
                currentOperandText.text = null;
                currentOperand = ""
            }
        }
        clearButton.setOnClickListener {
            previousOperandText.text = null
            currentOperandText.text = null
        }
        deleteButton.setOnClickListener {
            currentOperandText.text = currentOperandText.text.substring(0, currentOperandText.text.length - 1)
        }
        negativeButton.setOnClickListener {
            currentOperandText.text = (currentOperandText.text.toString().toDouble() * -1).toString()
        }
        equalsButton.setOnClickListener {

            try {
                val result = calculate(previousOperand, currentOperand, operator)
                previousOperandText.setText(buildString {
        append(previousOperand)
        append(operator)
        append(currentOperand)
        append("=")
    })
                currentOperandText.text = result.toString()
                previousOperand = result.toString()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }

        }
    }

    private fun calculate(
        previousOperand: String,
        currentOperand: String,
        operator: String
    ): Double {
        val firstNum = previousOperand.toDouble()
        val secondNum = currentOperand.toDouble()
        when (operator) {
            "/" -> return firstNum / secondNum
            "*" -> return firstNum * secondNum
            "-" -> return firstNum - secondNum
            "+" -> return firstNum + secondNum
            "mod" -> return firstNum % secondNum
        }
        //unreachable
        return 0.0;
    }
}