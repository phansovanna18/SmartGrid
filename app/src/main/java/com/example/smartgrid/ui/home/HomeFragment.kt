package com.example.smartgrid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgrid.fetchData.DashboardFetchData
import com.example.smartgrid.R




class HomeFragment : Fragment(){
//    override fun onBackPressed(): Boolean {
//        val startMain = Intent(Intent.ACTION_MAIN)
//        startMain.addCategory(Intent.CATEGORY_HOME)
//        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(startMain)
//        return true
//    }

    private lateinit var root:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = root.findViewById<RecyclerView>(R.id.dashboard_recyclerView)
        val data = DashboardFetchData(root.context,recyclerView,root.findViewById(R.id.barChart))
        data.execute()
    }
}