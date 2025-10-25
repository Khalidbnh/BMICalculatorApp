package com.example.app02_bmicalculatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import kotlin.text.isNotEmpty

class MainActivity : AppCompatActivity() {

    private lateinit var txt2Weight: TextView
    private var currentWeight: Int = 60
    private lateinit var txtAge: TextView
    private var currentAge: Int = 20
    private var currentHeight: Int = 172
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCalculate= findViewById<TextView>(R.id.btnCalculate)

        val txtvHeight = findViewById<TextView>(R.id.txtvHeight)
        val rsHeight = findViewById<RangeSlider>(R.id.rsHeight)

        val btnSubWeight = findViewById<FloatingActionButton>(R.id.btnSubWeight)
        val btnPlusWeight = findViewById<FloatingActionButton>(R.id.btnPlusWeight)
        txt2Weight = findViewById(R.id.txt2Weight)
        txtAge = findViewById(R.id.txtAge)
        val btnSubAge = findViewById<FloatingActionButton>(R.id.btnSubAge)
        val btnPlusAge = findViewById<FloatingActionButton>(R.id.btnPlusAge)

        rsHeight.setValues(172f)
        rsHeight.addOnChangeListener { _, value, _ ->
            currentHeight = value.toInt()
            txtvHeight.text = "$currentHeight CM"
        }

        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }

        btnSubWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }

        btnSubAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }

        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }


        buttonCalculate.setOnClickListener {
                val weight = txt2Weight.text.toString().toFloat()
                val height = currentHeight.toFloat()
                val bmi = calculateBmi(weight,height)
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("bmi Res", bmi) // Float
                startActivity(intent)
            }
    }
    fun calculateBmi(weight: Float, height: Float): Float {
        val heighttometers = height / 100 // height to meters
        val bmi = weight.toFloat() / (heighttometers * heighttometers)

        return bmi
    }

    private fun setWeight(){
        txt2Weight.text = currentWeight.toString()
    }

    private fun setAge(){
        txtAge.text = currentAge.toString()
    }
}