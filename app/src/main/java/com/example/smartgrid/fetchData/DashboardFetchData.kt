package com.example.smartgrid.fetchData

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgrid.AdapterClass.recyclerViewDashBoardCustomAdapter
import com.example.smartgrid.AdapterClass.thing
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

data class dataClassFetchData(val id:String,val data:String,val sensor:String,val date:String,val created_at:String,val updated_at:String)

class DashboardFetchData(context: Context, recyclerView: RecyclerView, chartView: com.github.mikephil.charting.charts.BarChart) : AsyncTask<Unit,Int,ArrayList<dataClassFetchData>>() {

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: ArrayList<dataClassFetchData>?) {
        super.onPostExecute(result)
        val labels = ArrayList<String>()
        val entries = ArrayList<BarEntry>()
        val device = ArrayList<thing>()
        var dataPower = 0.0f
        var sensor = "CT Sensor 1"
        var date = ArrayList<String>()
        for(i in 0 until result!!.size)
        {
            if(sensor == result[i].sensor)
            {
                date.add(result[i].date.substring(0,10))
            }
        }

        var _date = date.distinct()
        Log.d("_date",_date.toString())
        Log.d("sizeArray", result.size.toString())
        val arrayDataPerDay = ArrayList<Float>()
        for(i in 0 until _date.size)
        {
            var dataPerDay = 0.0f
            for(j in 0 until result.size)
            {
                if(result[j].sensor == sensor && result[j].date.substring(0,10) == _date[i])
                    dataPerDay += result[j].data.toFloat()
            }
            arrayDataPerDay.add(dataPerDay)
        }

        for(i in 0 until _date.size){
            entries.add(BarEntry(arrayDataPerDay[i],i))
            val dateString = _date.get(i)
            val date = SimpleDateFormat("yyyy-MM-dd").parse(dateString)
            val formateDate = SimpleDateFormat("dd")
            labels.add(formateDate.format(date!!))
        }
        val sumData = arrayDataPerDay.sum()

        device.add(thing(sensor,sumData.toString()+" KWH"))
        device.add(thing("Price (EDC)",(sumData*50).toString()+" Riel" + "  (${sumData*50/4000} USD)" ))
        val dataSet = BarDataSet(entries,"")
        val barData = BarData(labels,dataSet)

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS)

        chartView.animateY(500)
        chartView.data = barData
        chartView.fitScreen()
        chartView.setDescription("")

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)
        val adapterView = recyclerViewDashBoardCustomAdapter(device)
        recyclerView.adapter = adapterView
    }

    val url = "http://10.10.10.194/smartgridwebservice/getelectric.php"
    var data = ""

    var context : Context
    var chartView : com.github.mikephil.charting.charts.BarChart
    var recyclerView:RecyclerView

    init {
        this.context = context
        this.chartView= chartView
        this.recyclerView = recyclerView
    }

    override fun doInBackground(vararg p0: Unit?): ArrayList<dataClassFetchData> {
        val arrayData = ArrayList<dataClassFetchData>()
        try {
            data = URL(url).readText()
            val jsonObject = JSONObject(data)
            val jsonArray = JSONArray(jsonObject.get("electrics").toString())
            for(i in 0 until jsonArray.length()){
                val jObject = jsonArray.get(i) as JSONObject
                arrayData.add(dataClassFetchData(jObject.get("id").toString(),jObject.get("data").toString(),jObject.get("sensor").toString(),jObject.get("date").toString(),jObject.get("created_at").toString(),jObject.get("updated_at").toString()))
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
    }
}