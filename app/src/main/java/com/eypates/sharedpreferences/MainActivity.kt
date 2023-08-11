package com.eypates.sharedpreferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eypates.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var layoutBnd: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBnd = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
        }
        setContentView(layoutBnd.root)

        //SharedPreferences oluşturma
        sharedPreferences = this.getSharedPreferences("com.eypates.sharedpreferences", MODE_PRIVATE)

        layoutBnd.mainBtnSave.setOnClickListener {

            if (layoutBnd.mainTxtName.text.isEmpty()) {
                Toast.makeText(this, "Lütfen bir değer giriniz!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                sharedPreferences.edit().putString("SPUserName", layoutBnd.mainTxtName.text.toString()).apply()
                layoutBnd.mainTxtName.setText("")
            }
        }

        layoutBnd.mainBtnDel.setOnClickListener {

            val valueDel: String? = sharedPreferences.getString("SPUserName", "")

            if (valueDel != null) sharedPreferences.edit().remove("SPUserName").apply()
            else Toast.makeText(this, "Kullanıcı Bulunmadı", Toast.LENGTH_LONG).show()

        }

        layoutBnd.mainBtnRead.setOnClickListener {

            val lastValue: String? = sharedPreferences.getString("SPUserName", "Kayıt Bulunamadı!")
            layoutBnd.mainLblLastValue.text = "Son Okunan Değer: $lastValue"

        }
    }
}