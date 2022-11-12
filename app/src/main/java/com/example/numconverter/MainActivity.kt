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
        tvDecimal = findViewById(R.id.tvHex)
        tvBinary = findViewById(R.id.tvDecimal)
        tvOctal = findViewById(R.id.tvOctal)
        tvHexaDecimal = findViewById(R.id.tvBinary)
        //Right Views View Binding
        etInputDecimal = findViewById(R.id.etInputHex)
        tvBinaryOutput = findViewById(R.id.tvDecimalOutput)
        tvOctalOutput = findViewById(R.id.tvOctalOutput)
        tvHexaDecimalOutput = findViewById(R.id.tvBinaryOutput)
        //Buttons View Binding
        btnBinary = findViewById(R.id.btnDecimal)
        btnOctal = findViewById(R.id.btnOctal)
        btnHex = findViewById(R.id.btnBinary)

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

        val decimal = etInputDecimal.text.toString().toInt()
        // converting decimal to binary
        val binary = Integer.toBinaryString(decimal)
        // converting decimal to octal
        val octal = Integer.toOctalString(decimal)
        // converting decimal to hexadecimal
        val hex = Integer.toHexString(decimal)
        //Updating the UI
        tvBinaryOutput.text = binary.toString()
        tvOctalOutput.text = octal.toString()
        tvHexaDecimalOutput.text = hex.toString().uppercase()

    }



}

