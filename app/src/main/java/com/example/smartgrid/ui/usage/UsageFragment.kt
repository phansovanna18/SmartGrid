package com.example.smartgrid.ui.usage

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.smartgrid.R
import com.google.android.material.snackbar.Snackbar
import java.net.URL
import java.util.*

class UsageFragment : Fragment() , DatePickerDialog.OnDateSetListener{

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        if (startOrEnd == "Start")
            startDate.setText("$p1-$p2-$p3")
        else
            endDate.setText("$p1-$p2-$p3")
    }



    private fun showDatePicker(s:String){
        if (s == "Start")
            startOrEnd = "Start"
        else
            startOrEnd = "End"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val datePickerDialog = DatePickerDialog(root.context,this, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
            datePickerDialog.show()
        } else
            Toast.makeText(root.context,"Erorr",Toast.LENGTH_SHORT).show()
    }


    private lateinit var root:View
    private lateinit var startDate:EditText
    private lateinit var endDate:EditText
    private lateinit var startOrEnd:String
    private lateinit var btnOk :Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_usage, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()
        startDate = root.findViewById(R.id.usage_editText_startDate) as EditText
        btnOk = root.findViewById(R.id.usage_btn_OK) as Button
        btnOk.setOnClickListener {
            if(startDate.text.toString() != "" && endDate.text.toString() != "")
                Snackbar.make(btnOk,"Successful",Snackbar.LENGTH_SHORT).show()
//                URL("http://10.10.10.194/smartgridwebservice/lampapi/api/insert_schedule_data.php?start=\"${startDate.text}\"&end=\"${endDate.text}\"&device=\"CT Sensor 1\"&status=\"1\"").readText()
            else
                Snackbar.make(btnOk,"Put StartDate and EndDate",Snackbar.LENGTH_SHORT).show()
        }
        startDate.setOnClickListener {
            showDatePicker("Start")
        }
        endDate = root.findViewById(R.id.usage_editText_endDate) as EditText
        endDate.setOnClickListener {
            showDatePicker("End")
        }
    }
}