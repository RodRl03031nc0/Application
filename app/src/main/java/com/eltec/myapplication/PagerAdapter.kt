package com.eltec.myapplication

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.eltec.myapplication.model.ItemsP
import com.squareup.picasso.Picasso
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PagerAdapter(private val context: Context, private val imgUrl: List<ItemsP>) : PagerAdapter() {

    override fun getCount(): Int {
        return imgUrl.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val pos = imgUrl[position]
        val imageView = ImageView(context)
        Picasso.get().load(pos.imagen).into(imageView)

        container.addView(imageView)

        imageView.setOnClickListener { Toast.makeText(context, "true", Toast.LENGTH_SHORT).show() }

        if (pos.private) {
            context.startActivity(Intent(context, DetailActivity::class.java))
        } else {
            Toast.makeText(context, "value is false", Toast.LENGTH_SHORT).show()
        }

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}