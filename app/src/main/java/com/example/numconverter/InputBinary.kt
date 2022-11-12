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
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.android.material.snackbar.Snackbar
import kotlin.math.pow

private const val TAG = "InputBinary"
class InputBinary : AppCompatActivity() {
    //Left Views
    private lateinit var tvBinary: TextView
    private lateinit var tvDecimal: TextView
    private lateinit var tvOctal: TextView
    private lateinit var tvHexDecimal: TextView
    //Right Views
    private lateinit var etInputBinary: EditText
    private lateinit var tvDecimalOutput: TextView
    private lateinit var tvOctalOutput: TextView
    private lateinit var tvHexDecimalOutput: TextView
    //Buttons
    private lateinit var btnDecimal: Button
    private lateinit var btnOctal: Button
    private lateinit var btnHex: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_binary)
        //Left Views View Binding
        tvBinary = findViewById(R.id.tvHex)
        tvDecimal = findViewById(R.id.tvDecimal)
        tvOctal = findViewById(R.id.tvOctal)
        tvHexDecimal = findViewById(R.id.tvBinary)
        //Right Views View Binding
        etInputBinary= findViewById(R.id.etInputHex)
        tvDecimalOutput = findViewById(R.id.tvDecimalOutput)
        tvOctalOutput = findViewById(R.id.tvOctalOutput)
        tvHexDecimalOutput = findViewById(R.id.tvBinaryOutput)
        //Buttons View Binding
        btnDecimal = findViewById(R.id.btnDecimal)
        btnOctal = findViewById(R.id.btnOctal)
        btnHex = findViewById(R.id.btnBinary)

        //SnackBar implementation
        Snackbar.make(this.findViewById(R.id.binary_Layout),"Enter numbers only 0 & 1",Snackbar.LENGTH_INDEFINITE)
            .setAction("Ok"){ }
            .setActionTextColor(ContextCompat.getColor(this, android.R.color.white))
            .show()

        etInputBinary.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {  }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {  }

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "After Text Changed $s")
                convertNumber()
            }

        })

        //button click handling
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

        btnHex.setOnClickListener {
            val intent3  = Intent(this, InputHex::class.java)
            startActivity(intent3)
            Animatoo.animateSlideRight(this)
        }
    }

    private fun convertNumber() {
        //Null check at input
        if (etInputBinary.text.isEmpty()){
            tvDecimalOutput.text = ""
            tvOctalOutput.text = ""
            tvHexDecimalOutput.text = ""
            return
        }

        val binary = etInputBinary.text.toString().toLong()
        // converting decimal to binary
        val decimal = convertBinaryToDecimal(binary)
        // converting decimal to octal
        val octal = Integer.toOctalString(decimal)
        // converting decimal to hexadecimal
        val hex = Integer.toHexString(decimal)
        //Updating the UI
        tvDecimalOutput.text = decimal.toString()
        tvOctalOutput.text = octal.toString()
        tvHexDecimalOutput.text = hex.toString().uppercase()

    }

    private fun convertBinaryToDecimal(binary: Long): Int {
        var num = binary
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * 2.0.pow(i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }
}