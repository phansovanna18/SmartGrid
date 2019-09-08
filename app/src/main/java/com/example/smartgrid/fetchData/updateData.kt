package com.example.smartgrid.fetchData

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

data class updateDataClass(val id:String,val name:String,val status:String,val room:String,val created_at:String,val updated_at:String)

class updateData(context: Context) : AsyncTask<Unit,Int,ArrayList<updateDataClass>>() {

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: ArrayList<updateDataClass>?) {
        super.onPostExecute(result)
    }

    private val url = "http://10.10.10.194/smartgridwebservice/lampapi/api/update_id.php?id=1&status=1"

    override fun doInBackground(vararg p0: Unit?): ArrayList<updateDataClass> {
        var data = ""
        val arrayData = ArrayList<updateDataClass>()
        try {

            data = URL(url).readText()
            val jsonObject = JSONObject(data)
            val jsonArray = JSONArray(jsonObject.get("device").toString())
            for(i in 0 until jsonArray.length()){
                val jObject = jsonArray.get(i) as JSONObject
                arrayData.add(updateDataClass(jObject.get("id").toString(),jObject.get("name").toString(),jObject.get("status").toString(),jObject.get("room").toString(),jObject.get("created_at").toString(),jObject.get("updated_at").toString()))
            }
        }catch (e:MalformedURLException){
            data = e.toString()
        }catch (e:IOException){
            data = e.toString()
        }
        Log.d("data Error",data)
        return arrayData
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }
}