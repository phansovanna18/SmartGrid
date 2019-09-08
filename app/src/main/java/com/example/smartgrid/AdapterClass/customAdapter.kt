package com.example.smartgrid.AdapterClass

import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgrid.R
import org.json.JSONObject
import java.net.URL


data class device(val id:String, val name:String,val power:Boolean,val pic:Int)

class customAdapter(val devices: ArrayList<device>):RecyclerView.Adapter<customAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_layout, parent,false)
        return ViewHolder(view).listen{ position,_view ->
            val item = devices.get(position).name
            Toast.makeText(view.context,"$item.",Toast.LENGTH_SHORT).show()
        }
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, view:View) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), it)
        }
        return this
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = devices[position]
        holder.txtName.text = device.name
        holder.boolPower.isChecked = device.power
        holder.imageView.setImageResource(device.pic)
        holder.boolPower.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked->
            val SDK_INT = android.os.Build.VERSION.SDK_INT
            if(SDK_INT>8)
            {
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                if (isChecked) {
                    var url = URL("http://10.10.10.194/smartgridwebservice/lampapi/api/update_device_id.php?id=${device.id}&status=1").readText()
                } else {
                    var url = URL("http://10.10.10.194/smartgridwebservice/lampapi/api/update_device_id.php?id=${device.id}&status=0").readText()
                }
            }
        })
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val txtName = itemView.findViewById<TextView>(R.id.custom_layout_name_device)
        val boolPower = itemView.findViewById<Switch>(R.id.custom_layout_power_device)
        val imageView = itemView.findViewById<ImageView>(R.id.custom_layout_imageView)
//        init {
//            boolPower.setOnClickListener {
//                var SDK_INT = android.os.Build.VERSION.SDK_INT
//                if(SDK_INT>8)
//                {
//                    var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//                    StrictMode.setThreadPolicy(policy)
//                    if(boolPower.text == "true")
//                    {
//                        var url = URL("http://10.10.10.194/smartgridwebservice/lampapi/api/update_id.php?id=1&status=0").readText()
//                        var jsonObject = JSONObject(url)
//                        Toast.makeText(view.context,jsonObject.get("success").toString(),Toast.LENGTH_SHORT).show()
//                        boolPower.text = "false"
//                    }else{
//                        var url = URL("http://10.10.10.194/smartgridwebservice/lampapi/api/update_id.php?id=1&status=1").readText()
//                        var jsonObject = JSONObject(url)
//                        Toast.makeText(view.context,jsonObject.get("success").toString(),Toast.LENGTH_SHORT).show()
//                        boolPower.text = "true"
//                    }
//                }
//
//            }
//            boolPower.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked->
//                val SDK_INT = android.os.Build.VERSION.SDK_INT
//                if(SDK_INT>8)
//                {
//                    var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//                    StrictMode.setThreadPolicy(policy)
//                    if (isChecked) {
//                    var url = URL("http://10.10.10.194/smartgridwebservice/lampapi/api/update_id.php?id=1&status=1").readText()
//                    var jsonObject = JSONObject(url)
//                    Toast.makeText(view.context,jsonObject.get("success").toString(),Toast.LENGTH_SHORT).show()
//                } else {
//                    var url = URL("http://10.10.10.194/smartgridwebservice/lampapi/api/update_id.php?id=1&status=0").readText()
//                    var jsonObject = JSONObject(url)
//                    Toast.makeText(view.context,jsonObject.get("success").toString(),Toast.LENGTH_SHORT).show()
//                }
//                }
//            })
//        }
    }
}
