package com.example.smartgrid.AdapterClass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgrid.R
import java.util.ArrayList

data class settingMenuDataClass(val text:String)

class recyclerVeiwSettingCustomAdapter(val menus: ArrayList<settingMenuDataClass>): RecyclerView.Adapter<recyclerVeiwSettingCustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragement_setting_custom_layout_recylerview, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val text = menus[position]
//        holder.txtMenus.setText(text.text)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
//        val txtMenus = itemView.findViewById<TextView>(R.id.setting_custom_layout_textView)
    }
}