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
        etInputOctal = findViewById(R.id.etInputHex)
        tvDecimalOutput = findViewById(R.id.tvDecimalOutput)
        tvBinaryOutput = findViewById(R.id.tvOctalOutput)
        tvHexDecimalOutput = findViewById(R.id.tvBinaryOutput)
        //Buttons View Binding
        btnDecimal= findViewById(R.id.btnDecimal)
        btnBinary = findViewById(R.id.btnOctal)
        btnHex = findViewById(R.id.btnBinary)

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

        val  octal= etInputOctal.text.toString().toInt()
        // converting decimal to binary
        val decimal = convertOctalToDecimal(octal)
        // converting decimal to octal
        val binary = Integer.toBinaryString(decimal)
        // converting decimal to hexadecimal
        val hex = Integer.toHexString(decimal)
        //Updating the UI
        tvDecimalOutput.text = decimal.toString()
        tvBinaryOutput.text = binary.toString()
        tvHexDecimalOutput.text = hex.toString().uppercase()
    }

    private fun convertOctalToDecimal(num: Int): Int {
        var octal = num
        var decimalNumber = 0
        var i = 0
        var remainder: Int

        while (octal != 0) {
            remainder = octal % 10
            decimalNumber += (remainder * 8.0.pow(i.toDouble())).toInt()
            ++i
            octal /= 10
        }

        return decimalNumber
    }
}