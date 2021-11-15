package com.hieuwu.groceriesstore.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.bindImage

class ViewPagerAdapter(val context: Context) : PagerAdapter() {

    private val images = arrayOf(
        "https://firebasestorage.googleapis.com/v0/b/groceries-store-ad0eb.appspot.com/o/slider-1.jpg?alt=media&token=468896eb-8083-4a79-ba18-6af6a7c3e3eb",
        "https://firebasestorage.googleapis.com/v0/b/groceries-store-ad0eb.appspot.com/o/slider-3.jpg?alt=media&token=94a991e9-75a1-45ae-9439-3dc94522eb9f",
        "https://firebasestorage.googleapis.com/v0/b/groceries-store-ad0eb.appspot.com/o/slider-2.jpg?alt=media&token=c7fcd8de-3bb6-48e2-9a63-f393d4b45c98",
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.layout_image_layout, null);
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        bindImage(imageView, images[position])
        container.addView(view, 0);

        return view
    }

    override fun getCount() = images.size


    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}