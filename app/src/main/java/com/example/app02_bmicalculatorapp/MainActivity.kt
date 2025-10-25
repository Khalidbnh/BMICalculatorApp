package com.example.app02_bmicalculatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.RangeSlider
import kotlin.text.isNotEmpty

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textHeight = findViewById<EditText>(R.id.txtHeight)
        val textWeight = findViewById<EditText>(R.id.txtWeight)
        val buttonCalculate= findViewById<TextView>(R.id.btnCalculate)

        val txtvHeight = findViewById<TextView>(R.id.txtvHeight)
        val rsHeight = findViewById<RangeSlider>(R.id.rsHeight)

        rsHeight.addOnChangeListener { _, value, _ ->
            val height = value.toInt()
            txtvHeight.text = "$height CM"
        }

        buttonCalculate.setOnClickListener {
            if (textHeight.text.isNotEmpty() && textWeight.text.isNotEmpty()) {
                val weight = textWeight.text.toString().toFloat()
                val height = textHeight.text.toString().toFloat()
                val bmi = calculateBmi(weight,height)
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("bmi Res", bmi) // Float
                startActivity(intent)
            } else{
                textHeight.error = "Please enter valid numbers"
                textWeight.error = "Please enter valid numbers"
                }
            }
    }
    fun calculateBmi(weight: Float, height: Float): Float {
        val heighttometers = height / 100 // height to meters
        val bmi = weight.toFloat() / (heighttometers * heighttometers)

        return bmi
    }
}