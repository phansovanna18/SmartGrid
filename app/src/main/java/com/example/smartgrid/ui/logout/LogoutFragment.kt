package com.example.smartgrid.ui.logout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.smartgrid.fetchData.UsersLoginSharedPreference
import com.example.smartgrid.LoginActivity
import com.example.smartgrid.R


class LogoutFragment: Fragment() {

    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_logout, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()
        val btnNo = root.findViewById<Button>(R.id.logout_btnNo)
        val btnYes = root.findViewById<Button>(R.id.logout_btnYes)
        btnNo.setOnClickListener {
            activity!!.onBackPressed()
        }
        btnYes.setOnClickListener {
            val usersLoginSharedPreference = UsersLoginSharedPreference(root.context)
            usersLoginSharedPreference.setLogin(false)
            val intent = Intent(root.context,LoginActivity::class.java)
            startActivity(intent)
        }

    }
}