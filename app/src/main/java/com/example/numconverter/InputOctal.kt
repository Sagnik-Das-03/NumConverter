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

private const val TAG = "InputOctal"
class InputOctal : AppCompatActivity() {
    //Right Views
    private lateinit var etInputOctal: EditText
    private lateinit var tvDecimalOutput: TextView
    private lateinit var tvBinaryOutput: TextView
    private lateinit var tvHexDecimalOutput: TextView
    //Buttons
    private lateinit var btnDecimal: Button
    private lateinit var btnBinary: Button
    private lateinit var btnHex: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_octal)
        //Right Views View Binding
        etInputOctal = findViewById(R.id.etInput)
        tvDecimalOutput = findViewById(R.id.tvDecimalOutput)
        tvBinaryOutput = findViewById(R.id.tvOctalOutput)
        tvHexDecimalOutput = findViewById(R.id.tvBinaryOutput)
        //Buttons View Binding
        btnDecimal= findViewById(R.id.btnDecimal)
        btnBinary = findViewById(R.id.btnOctal)
        btnHex = findViewById(R.id.btnHex)

        // SnackBar implementation
       Snackbar.make(this.findViewById(R.id.octal_Layout),"Enter only numbers between 0 to 7",Snackbar.LENGTH_INDEFINITE)
            .setAction("Ok") {}
            .setActionTextColor(ContextCompat.getColor(this, android.R.color.white))
            .show()

        etInputOctal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {  }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {  }

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "After Text Changed $s")
                convertNumber()
            }

        })
        //button click handling
        btnDecimal.setOnClickListener {
            val intent1 = Intent(this,MainActivity::class.java)
            startActivity(intent1)
            Animatoo.animateSlideRight(this)
        }
        btnBinary.setOnClickListener {
            val intent2  = Intent(this, InputBinary::class.java)
            startActivity(intent2)
            Animatoo.animateSlideRight(this)
        }
        btnHex.setOnClickListener {
            val intent3  = Intent(this, InputHex::class.java)
            startActivity(intent3)
            Animatoo.animateSlideRight(this)
        }
    }

    private fun convertNumber() {
        //Null check at input
        if (etInputOctal.text.isEmpty()){
            tvDecimalOutput.text = ""
            tvBinaryOutput.text = ""
            tvHexDecimalOutput.text = ""
            return
        }
        //Storing input in a variable
        val octal= (LEADING_ZERO+etInputOctal.text.toString())
        // 1 -> if statements  //Handles Float input
        // 2 -> else statements //Handles Integer input

        // converting octal to decimal
        if(octal.contains(".")){
            val decimal = calculateDecimal(octal)
            tvDecimalOutput.text = decimal
        }
        else{
            val decimal = convertOctalToDecimal(octal.toLong())
            tvDecimalOutput.text = decimal.toString()

        }

        // converting octal to binary
        if(octal.contains(".")){
            val decimal = calculateDecimal(octal)
            val binary = convertDecimalToBinary(decimal.toDouble())
            tvBinaryOutput.text= binary
        }else{
            val decimal = convertOctalToDecimal(octal.toLong())
            val binary = decimal.toString(2)
            tvBinaryOutput.text= binary
        }

        // converting decimal to hexadecimal
        if (octal.contains(".")){
            val decimal = calculateDecimal(octal)
            val hex = convertDecimalToHex(decimal.toDouble())
            tvHexDecimalOutput.text= hex
        }else{
            val decimal = convertOctalToDecimal(octal.toLong())
            val hex = decimal.toString(16)
            tvHexDecimalOutput.text = hex
        }

    }

    private fun convertOctalToDecimal(num: Long): Long {
        var octal = num
        var decimalNumber : Long= 0
        var i = 0
        var remainder: Long

        while (octal.toInt() != 0) {
            remainder = octal % 10
            decimalNumber += (remainder * 8.0.pow(i.toDouble())).toLong()
            ++i
            octal /= 10
        }

        return decimalNumber
    }

    private fun convertDecimalToBinary(decimal : Double): String {
        val lShift = (decimal * 2.0.pow(8.0)).toLong()
        val temp = lShift.toString(2).padStart(9,'0')
        val revBinary = temp.reversed()
        return (revBinary.substring(0, 8)+"."+revBinary.substring(8)).reversed()
    }

    private fun convertDecimalToHex(decimal: Double): String {
        val lShift = (decimal*16.0.pow(8.0)).toLong()
        val temp = lShift.toString(16).padStart(9,'0')
        val revHex = temp.reversed()
        return (revHex.substring(0, 8)+"."+revHex.substring(8)).reversed().uppercase()
    }

    private fun calculateDecimal(octal: String): String{
        val str = octal.split(".")
        val decimalFull =(str[0]+str[1].padEnd(8,'0')).toLong()
        val decimal = convertOctalToDecimal(decimalFull)
        val d = (decimal/ 8.0.pow(8))
        return d.toString()
    }

}