package com.example.numconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow

const val LEADING_ZERO = "0"
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    //Left Views
    private lateinit var tvDecimal: TextView
    private lateinit var tvBinary: TextView
    private lateinit var tvOctal: TextView
    private lateinit var tvHexaDecimal: TextView
    //Right Views
    private lateinit var etInputDecimal: EditText
    private lateinit var tvBinaryOutput: TextView
    private lateinit var tvOctalOutput: TextView
    private lateinit var tvHexaDecimalOutput: TextView
    //Buttons
    private lateinit var btnBinary: Button
    private lateinit var btnOctal: Button
    private lateinit var btnHex: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Left Views View Binding
        tvDecimal = findViewById(R.id.tvDecimal)
        tvBinary = findViewById(R.id.tvBinary)
        tvOctal = findViewById(R.id.tvOctal)
        tvHexaDecimal = findViewById(R.id.tvHex)
        //Right Views View Binding
        etInputDecimal = findViewById(R.id.etInput)
        tvBinaryOutput = findViewById(R.id.tvDecimalOutput)
        tvOctalOutput = findViewById(R.id.tvOctalOutput)
        tvHexaDecimalOutput = findViewById(R.id.tvBinaryOutput)
        //Buttons View Binding
        btnBinary = findViewById(R.id.btnBinary)
        btnOctal = findViewById(R.id.btnOctal)
        btnHex = findViewById(R.id.btnHex)

        //SnackBar implementation
        Snackbar.make(this.findViewById(R.id.mainLayout),"Enter a Number", Snackbar.LENGTH_INDEFINITE)
            .setAction("Ok"){ }
            .setActionTextColor(ContextCompat.getColor(this, android.R.color.white))
            .show()

        etInputDecimal.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {  }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {  }

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "After Text Changed $s")
                convertNumber()
            }

        })
        //button click handling
        btnBinary.setOnClickListener {
            val intent = Intent(this, InputBinary::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

        btnOctal.setOnClickListener {
            val intent = Intent(this, InputOctal::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

        btnHex.setOnClickListener {
            val intent = Intent(this, InputHex::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }
    }

    private fun convertNumber()  {
        //Null check at input
        if (etInputDecimal.text.isEmpty()) {
            tvBinaryOutput.text = ""
            tvOctalOutput.text = ""
            tvHexaDecimalOutput.text = ""
            return
        }

        val decimal = LEADING_ZERO+etInputDecimal.text.toString()
        // converting decimal to binary
        if(decimal.toString().contains(".")){
            val binary = convertDecimalToBinary(decimal.toDouble())
            tvBinaryOutput.text = binary
        }else{
            val binary = decimal.toLong().toString(2)
            tvBinaryOutput.text = binary
        }
        // converting decimal to octal
        if(decimal.toString().contains(".")){
            val octal = convertDecimalToOctal(decimal.toDouble())
            tvOctalOutput.text = octal
        }else{
            val octal = decimal.toLong().toString(8)
            tvOctalOutput.text = octal
        }
        // converting decimal to hexadecimal
        if (decimal.toString().contains(".")){
            val hex = convertDecimalToHex(decimal.toDouble())
            tvHexaDecimalOutput.text = hex
        }else{
            val hex = decimal.toLong().toString(16)
            tvHexaDecimalOutput.text = hex
        }

    }
    private fun convertDecimalToBinary(decimal: Double): String {
        val lShift = (decimal * 2.0.pow(8.0)).toLong()
        val temp = lShift.toString(2).padStart(9,'0')
        val revBinary = temp.reversed()
        return (revBinary.substring(0, 8) + "." + revBinary.substring(8)).reversed()
    }

    private fun convertDecimalToOctal(decimal : Double): String {
        val lShift = (decimal * 8.0.pow(8.0)).toLong()
        val temp = lShift.toString(8).padStart(9,'0')
        val revOctal = temp.reversed()
        return (revOctal.substring(0, 8)+"."+revOctal.substring(8)).reversed()
    }
    private fun convertDecimalToHex(decimal: Double): String {
        val lShift = (decimal*16.0.pow(8.0)).toLong()
        val temp = lShift.toString(16).padStart(9,'0')
        val revHex = temp.reversed()
        return (revHex.substring(0, 8)+"."+revHex.substring(8)).reversed().uppercase()
    }


}

