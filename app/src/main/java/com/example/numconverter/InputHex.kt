package com.example.numconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow

private const val TAG = "InputHex"
class InputHex : AppCompatActivity() {
    //Left Views
    private lateinit var tvHexDecimal: TextView
    private lateinit var tvDecimal: TextView
    private lateinit var tvOctal: TextView
    private lateinit var tvBinary: TextView
    //Right Views
    private lateinit var etInputHex: EditText
    private lateinit var tvDecimalOutput: TextView
    private lateinit var tvOctalOutput: TextView
    private lateinit var tvBinaryOutput: TextView
    //Buttons
    private lateinit var btnDecimal: Button
    private lateinit var btnOctal: Button
    private lateinit var btnBinary: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_hex)
        //Left Views View Binding
        tvHexDecimal = findViewById(R.id.tvHex)
        tvDecimal = findViewById(R.id.tvDecimal)
        tvOctal = findViewById(R.id.tvOctal)
        tvBinary = findViewById(R.id.tvBinary)
        //Right Views View Binding
        etInputHex= findViewById(R.id.etInputHex)
        tvDecimalOutput = findViewById(R.id.tvDecimalOutput)
        tvOctalOutput = findViewById(R.id.tvOctalOutput)
        tvBinaryOutput = findViewById(R.id.tvBinaryOutput)
        //Buttons View Binding
        btnDecimal = findViewById(R.id.btnDecimal)
        btnOctal = findViewById(R.id.btnOctal)
        btnBinary = findViewById(R.id.btnBinary)

        //Snackbar implementation
        Snackbar.make(this.findViewById(R.id.hex_Layout),"Enter only numbers & characters between A & F",Snackbar.LENGTH_INDEFINITE)
            .setAction("Ok"){ }
            .setActionTextColor(ContextCompat.getColor(this, android.R.color.white))
            .show()

        etInputHex.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {  }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {  }

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "After Text Changed $s")
                convertNumber()
            }

        })

        btnDecimal.setOnClickListener {
            val intent1  = Intent(this, MainActivity::class.java)
            startActivity(intent1)
            Animatoo.animateSlideRight(this)
        }

        btnOctal.setOnClickListener {
            val intent2  = Intent(this, InputOctal::class.java)
            startActivity(intent2)
            Animatoo.animateSlideRight(this)
        }

        btnBinary.setOnClickListener {
            val intent3  = Intent(this, InputBinary::class.java)
            startActivity(intent3)
            Animatoo.animateSlideRight(this)
        }
    }

    private fun convertNumber() {
        if (etInputHex.text.isEmpty()){
            tvDecimalOutput.text = ""
            tvOctalOutput.text = ""
            tvBinaryOutput.text = ""
            return
        }

        //calculating and converting values from input
        val hex = (LEADING_ZERO+etInputHex.text.toString().uppercase())

        // converting hex to decimal
       if(hex.contains(".")){
           val decimal = convertHexToDecimal(hex)
           tvDecimalOutput.text = decimal
       }else{
           val decimal = hex.toLong(16)
           tvDecimalOutput.text = decimal.toString()
       }
        //converting hex to octal
        if (hex.contains(".")){
            val decimal = convertHexToDecimal(hex)
            val octal = convertDecimalToOctal(decimal.toDouble())
            tvOctalOutput.text = octal
        }else{
            val decimal = hex.toLong(16)
            val octal = decimal.toString(8)
            tvOctalOutput.text = octal
        }

        // converting hex to binary
        if(hex.contains(".")){
            val decimal = convertHexToDecimal(hex)
            val binary = convertDecimalToBinary(decimal.toDouble())
            tvBinaryOutput.text = binary
        }else{
            val decimal = hex.toLong(16)
            val binary = decimal.toString(2)
            tvBinaryOutput.text = binary
            tvBinaryOutput.movementMethod = ScrollingMovementMethod()
        }



    }

    private fun convertHexToDecimal(hex: String): String {
        val spt = hex.split(".")
        val fullHex = (spt[0] + spt[1].padEnd(8, '0'))
        val fullDecimal = fullHex.toLong(16)
        val decimalFloat = (fullDecimal / 16.0.pow(8)).toString()
        val decimal = decimalFloat.split(".")
        return decimal[0] + "." + decimal[1].padEnd(8, '0')
    }
    private fun convertDecimalToBinary(decimal : Double): String {
        val lShift = (decimal * 2.0.pow(8.0)).toLong()
        val temp = lShift.toString(2).padStart(9,'0')
        val revBinary = temp.reversed()
        return (revBinary.substring(0, 8)+"."+revBinary.substring(8)).reversed()
    }

    private fun convertDecimalToOctal(decimal : Double): String {
        val lShift = (decimal * 8.0.pow(8.0)).toLong()
        val temp = lShift.toString(8).padStart(9,'0')
        val revOctal = temp.reversed()
        return (revOctal.substring(0, 8)+"."+revOctal.substring(8)).reversed()
    }


}