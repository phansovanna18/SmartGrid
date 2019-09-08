package com.example.smartgrid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.smartgrid.fetchData.UsersLoginSharedPreference
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var login : UsersLoginSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login = UsersLoginSharedPreference(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        if(login.getLogin()!!)
            logIn()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }

    fun onClick(view: View){
        when(view.id){
            R.id.login_SignIn -> {
                checkUser(login_EditText_user.text.toString(),login_EditText_password.text.toString())
            }
            R.id.login_Register ->{
                intent = Intent(this@LoginActivity,RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun logIn(){
        login.setLogin(true)
        val myIntent = Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(myIntent)
    }

    private fun checkUser(email:String,password:String)
    {
        if(email == "phansovanna18@gmail.com" && password == "1234"){
            login.setEmail(email)
            login.setPassword(password)
            logIn()
        }
        else
            Snackbar.make(login_SignIn,"Wrong Email or Password",Snackbar.LENGTH_SHORT).show()
    }
}
