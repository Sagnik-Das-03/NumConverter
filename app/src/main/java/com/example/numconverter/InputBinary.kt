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

        val binary = "0"+etInputBinary.text.toString()
        // converting decimal to binary
       if(binary.contains(".")){
           val decimal = calculateDecimal(binary)
           tvDecimalOutput.text = decimal.toString()
       }
        else{
            val decimal = convertBinaryToDecimal(binary.toLong())
           tvDecimalOutput.text = decimal.toString()
       }


        // converting decimal to octal
        if(binary.contains(".")){
            val decimal =  calculateDecimal(binary)
            val octal = convertDecimalToOctal(decimal.toDouble())
            tvOctalOutput.text = octal
        }else{
            val decimal = convertBinaryToDecimal(binary.toLong())
            val octal = decimal.toString(8)
            tvOctalOutput.text= octal
        }
        // converting decimal to hexadecimal
        if(binary.contains(".")){
            val decimal = calculateDecimal(binary)
            val hex = convertDecimalToHex(decimal.toDouble())
            tvHexDecimalOutput.text= hex
        }else{
            val decimal = convertBinaryToDecimal(binary.toLong())
            val hex = decimal.toString(16)
            tvHexDecimalOutput.text = hex
        }

    }


    private fun convertBinaryToDecimal(binary: Long):  Long{
        var num = binary
        var decimalNumber: Long = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * 2.0.pow(i.toDouble())).toLong()
            ++i
        }
        return decimalNumber
    }

    private fun convertDecimalToOctal(decimal : Double): String {
        if(decimal<0.2){
            return "0.0"
        }
        val lShift = (decimal * 8.0.pow(8.0)).toLong()
        val temp = lShift.toString(8)
        val revOctal = temp.reversed()
        return (revOctal.substring(0, 8)+"."+revOctal.substring(8)).reversed()
    }

    private fun convertDecimalToHex(decimal: Double): String {
        if(decimal<0.2){
            return "0.0"
        }
        val lShift = (decimal*16.0.pow(8.0)).toLong()
        val temp = lShift.toString(16)
        val revHex = temp.reversed()
        return (revHex.substring(0, 8)+"."+revHex.substring(8)).reversed().uppercase()
    }

    private fun calculateDecimal(binary: String): String{
        val str = binary.split(".")
        if(str[0].isEmpty() and str[1].isNotEmpty()){
            return "0.0"
        }
        val decimalFull =(str[0]+str[1]).toLong()
        val decimal = convertBinaryToDecimal(decimalFull)
        val d = (decimal/ 2.0.pow(8))
        return d.toString()
    }

}