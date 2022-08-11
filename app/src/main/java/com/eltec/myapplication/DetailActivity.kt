package com.eltec.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.eltec.myapplication.databinding.ActivityDetailBinding
import org.json.JSONException

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var requestQueue: RequestQueue
    private lateinit var pro: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        pro = bundle?.get("p") as String

        requestQueue = Volley.newRequestQueue(this)

//        this is normal
//        binding.detailWb.loadUrl(pro)
        parseJSON()
    }

    private fun parseJSON() {
        val url = pro

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()) {
                        val item = jsonArray.getJSONObject(i)
                        val title = item.getString("title")
                        val urlC = item.getString("url")

//                     from data
                        binding.detailWb.loadUrl(urlC)
                        supportActionBar?.title = title
                        Log.e("TAG", "parseJSON: $title")
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error -> error.printStackTrace() }
        requestQueue.add(request)

    }
}