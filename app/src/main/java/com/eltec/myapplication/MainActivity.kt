package com.eltec.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eltec.myapplication.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//example
        var url: MutableList<String> = mutableListOf()
        url.add("https://images.unsplash.com/photo-1656414562758-8ee23647872e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80")
        url.add("https://images.unsplash.com/photo-1656464411931-be8ee35d8c6a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1632&q=80")
        url.add("https://images.unsplash.com/photo-1656376406183-506c85f2cdf2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80")


        Retrofit.Builder()
            .baseUrl("https://tiradodev.github.io/appLite/pruebaAzteca.json")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding.mainVp.adapter = PagerAdapter(this, url)

    }
}