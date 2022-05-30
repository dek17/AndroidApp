package com.example.pertemuan12_71190423

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherBtn.setOnClickListener {
            weatherCheck(edtCityName.text.toString())

        }




    }

    fun weatherCheck(city : String){
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=8e57e934c5570c019114023dfdf75051"
        val Request = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                val data = JSONObject(response)
//                weather
                val currWeather = data.getJSONArray("list").getJSONObject(0).
                getJSONArray("weather").getJSONObject(0).getString("description")
//                temp
                val temp = data.getJSONArray("list").getJSONObject(0).
                getJSONObject("main").getDouble("temp")

                result.text = "Today \n\n ${currWeather} (${String.format("%.2f", temp - 273.15).toDouble()}\u2103)"

//                Tommorrow
//                weather
                val TomWeather = data.getJSONArray("list").getJSONObject(1).
                getJSONArray("weather").getJSONObject(0).getString("description")
//                temp Tommorow
                val tomTemp = data.getJSONArray("list").getJSONObject(1).
                getJSONObject("main").getDouble("temp")

                resultTom.text = "Tommorow \n\n ${TomWeather} (${String.format("%.2f", tomTemp - 273.15).toDouble()}\u2103)"

//                Tommorrow
//                weather
                val TomTomWeather = data.getJSONArray("list").getJSONObject(2).
                getJSONArray("weather").getJSONObject(0).getString("description")
//                temp Tommorow
                val tomTomTemp = data.getJSONArray("list").getJSONObject(2).
                getJSONObject("main").getDouble("temp")

                resultTomTom.text = "The Day After Tommorow \n\n ${TomTomWeather} (${String.format("%.2f", tomTomTemp - 273.15).toDouble()}\u2103)"





            },
            Response.ErrorListener {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG).show()

            })


        queue.add(Request)

    }
}
