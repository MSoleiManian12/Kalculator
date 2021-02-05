package com.soleimanian.kalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Numbers
        num0.setOnClickListener { appendVal("0") }
        num1.setOnClickListener { appendVal("1") }
        num2.setOnClickListener { appendVal("2") }
        num3.setOnClickListener { appendVal("3") }
        num4.setOnClickListener { appendVal("4") }
        num5.setOnClickListener { appendVal("5") }
        num6.setOnClickListener { appendVal("6") }
        num7.setOnClickListener { appendVal("7") }
        num8.setOnClickListener { appendVal("8") }
        num9.setOnClickListener { appendVal("9") }
        numDot.setOnClickListener { appendVal(".") }
        // Operators
        startBracket.setOnClickListener { appendVal(" ( ") }
        closeBracket.setOnClickListener { appendVal(" ) ") }
        actionDivide.setOnClickListener { appendVal(" / ") }
        actionMultiply.setOnClickListener { appendVal(" * ") }
        actionMinus.setOnClickListener { appendVal(" - ") }
        actionAdd.setOnClickListener { appendVal(" + ") }
        clear.setOnClickListener { appendVal("", true) }
        actionBack.setOnClickListener {
            val ex = placeholder.text.toString();
            if (ex.isNotEmpty()) {
                placeholder.text = ex.substring(0, ex.length - 1)
            }
        }
        actionEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(placeholder.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    answer.text = longResult.toString()
                } else {
                    answer.text = result.toString()
                }
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                Log.d("Exception", "Message: ${e.message}")
            }
        }
    }

    private fun appendVal (string: String, isClear: Boolean = false) {
        if (isClear) {
            placeholder.text = ""
            answer.text = ""
        } else {
            placeholder.append(string)
        }
    }
}