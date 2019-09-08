package com.example.smartgrid.ui.control_device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgrid.fetchData.fetchDataUser
import com.example.smartgrid.R

class ControlDeviceFragment : Fragment(){

    private lateinit var root:View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_control_device, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = root.findViewById<RecyclerView>(R.id.on_off_device_recyclerView)
        val progressBar = root.findViewById<ProgressBar>(R.id.on_off_device_progressBar)
        var fetchDataUser = fetchDataUser(root.context,recyclerView,progressBar)
        fetchDataUser.execute()
    }
}