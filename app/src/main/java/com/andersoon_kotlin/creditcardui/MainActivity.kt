package com.andersoon_kotlin.creditcardui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import com.andersoon_kotlin.creditcardui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var current = ""

    private var creditCardNumberIntentExtra = ""
    private var monthAndYearIntentExtra = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editCreditCardNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current){

                    val cardNumber = s.toString().replace(nonDigits,"")

                    if (cardNumber.length <= 16){
                        current = cardNumber.chunked(4).joinToString(" ")
                        s!!.filters = arrayOfNulls<InputFilter>(0)
                    }

                    s!!.replace(0,s.length,current,0,current.length)
                    creditCardNumberIntentExtra = s.toString()
                    Log.d("creditNumber",creditCardNumberIntentExtra)
                }
            }

        })

        binding.editMonthAndYear.addTextChangedListener(object  : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length == 4){
                    monthAndYear(s.toString())
                }
            }

        })



    }
    companion object{
        private val nonDigits =Regex("\\D")
    }

    private fun monthAndYear(s: String){
        if (s.length == 4){
            val  monthAndYear = s.substring(0,4).chunked(2).joinToString("/")
            binding.editMonthAndYear.setText(monthAndYear)
            monthAndYearIntentExtra = monthAndYear
            Log.d("my",monthAndYearIntentExtra)
        }
    }
}
















