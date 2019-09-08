package com.example.smartgrid.fetchData


import android.content.Context
import android.os.AsyncTask
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgrid.AdapterClass.customAdapter
import com.example.smartgrid.AdapterClass.device
import com.example.smartgrid.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class fetchDataUser(context: Context,recyclerView: RecyclerView,progressBar: ProgressBar) : AsyncTask<Unit,Int,ArrayList<dataClass>>() {

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: ArrayList<dataClass>?) {
        super.onPostExecute(result)

        val icon = arrayOf(R.drawable.icon_airconditioner,R.drawable.light,R.drawable.tv)
        val device = ArrayList<device>()
        for (i in 0 until result!!.size){
            var power = false
            if (result[i].status == "1")
                power = true
            device.add(device(result[i].id, result[i].name, power,icon[i%3]))
        }
        val adapterView = customAdapter(device)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)
        recyclerView.adapter = adapterView
        progressBar.visibility =View.GONE
    }

    val url = "http://10.10.10.194/smartgridwebservice/lampapi/api/device_read_all.php"
    var data = ""

    var context : Context
    var recyclerView:RecyclerView
    var progressBar:ProgressBar
    init {
        this.progressBar = progressBar
        this.context = context
        this.recyclerView = recyclerView
    }

    override fun doInBackground(vararg p0: Unit?): ArrayList<dataClass> {
        val arrayData = ArrayList<dataClass>()
        try {
            data = URL(url).readText()
            val jsonObject = JSONObject(data)
            val jsonArray = JSONArray(jsonObject.get("devices").toString())
            for(i in 0 until jsonArray.length()){
                val jObject = jsonArray.get(i) as JSONObject
                arrayData.add(dataClass(jObject.get("id").toString(),jObject.get("name").toString(),jObject.get("status").toString(),jObject.get("room").toString(),jObject.get("created_at").toString(),jObject.get("updated_at").toString()))
            }
        }catch (e:MalformedURLException){
            data = e.toString()
        }catch (e:IOException){
            data = e.toString()
        }
        return arrayData
    }

    override fun onPreExecute() {
        super.onPreExecute()
        progressBar.visibility =View.VISIBLE
    }
}