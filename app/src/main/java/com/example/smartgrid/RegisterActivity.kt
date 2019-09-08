package com.example.smartgrid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun click(view: View){
        when(view.id){
            R.id.register_textView_Login ->
                finish()
        }
    }
}
