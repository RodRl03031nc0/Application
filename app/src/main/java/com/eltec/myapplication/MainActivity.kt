package com.eltec.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.eltec.myapplication.databinding.ActivityMainBinding
import com.eltec.myapplication.model.Item
import com.eltec.myapplication.model.ItemAdapter
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var requestQueue: RequestQueue
    private lateinit var listItem: MutableList<Item>
    private lateinit var adapterItem: ItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)

        initRv()
        parseJSON()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun parseJSON() {
        val url = "https://tiradodev.github.io/appLite/pruebaAzteca.json?format=json/"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("items")
                    for (i in 0 until jsonArray.length()) {
                        val item = jsonArray.getJSONObject(i)
                        val pro = item.getString("programa")
                        val img = item.getString("imagen")
                        val priv = item.getString("private")

                        listItem.add(Item(pro, img, priv))
                        Log.e("TAG", "parseJSON: $listItem" )
                    }

                    adapterItem.notifyDataSetChanged()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error -> error.printStackTrace() }
        requestQueue.add(request)

    }

    private fun initRv() {
        listItem = mutableListOf()

        adapterItem = ItemAdapter(this,listItem)
        val llManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.mainVp.layoutManager = llManager


        binding.mainVp.adapter = adapterItem
    }
}