package com.example.smartgrid.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartgrid.AdapterClass.customAdapter
import com.example.smartgrid.AdapterClass.recyclerVeiwSettingCustomAdapter
import com.example.smartgrid.AdapterClass.settingMenuDataClass
import com.example.smartgrid.R
import kotlinx.android.synthetic.main.fragment_setting.*
import java.util.ArrayList

class SettingFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_setting, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()
//        var list_text = ArrayList<settingMenuDataClass>()
//        list_text.add(settingMenuDataClass("Account Setting"))
//        list_text.add(settingMenuDataClass("Language"))

//        val adapterView = recyclerVeiwSettingCustomAdapter(list_text)
//        setting_recyclerVeiw.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)
//        setting_recyclerVeiw.adapter = adapterView
    }
}