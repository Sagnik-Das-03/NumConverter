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
        val hex = etInputHex.text.toString().uppercase()
        // converting hex to decimal
        val decimal = hex.toInt(16)
        //converting decimal to octal
        val octal = Integer.toOctalString(decimal)
        //converting decimal to binary
        val binary = Integer.toBinaryString(decimal)
        //Updating the UI
        tvDecimalOutput.text = decimal.toString()
        tvOctalOutput.text = octal.toString()
        tvBinaryOutput.text = binary.toString()

    }
}