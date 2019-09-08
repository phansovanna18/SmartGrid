package com.example.smartgrid.AdapterClass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgrid.R
import java.util.ArrayList

data class thing(val name:String,val data:String)

class recyclerViewDashBoardCustomAdapter(val things: ArrayList<thing>): RecyclerView.Adapter<recyclerViewDashBoardCustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_dashboard_recyclerview, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return things.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = things[position]
        holder.txtName.text = device.name
        holder.txtPowerData.text = device.data
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtName = itemView.findViewById<TextView>(R.id.custom_layout_dashboard_sensor)
        val txtPowerData = itemView.findViewById<TextView>(R.id.custom_layout_dashboard_powerData)
    }
}