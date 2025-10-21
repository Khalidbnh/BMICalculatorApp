package com.example.app02_bmicalculatorapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)

        val textResult = findViewById<TextView>(R.id.txtResult)
        val textAdvice = findViewById<TextView>(R.id.txtAdvice)
        val buttonBack = findViewById<TextView>(R.id.btnBack)

        val bmi = intent.getFloatExtra("bmi Res", 0f)
        println("DEBUG -- Received BMI: $bmi")
        textResult.text = "Your BMI: %.2f".format(bmi)

        val message = when{
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }
        textAdvice.text = message

        buttonBack.setOnClickListener {
            finish()
        }
    }
}