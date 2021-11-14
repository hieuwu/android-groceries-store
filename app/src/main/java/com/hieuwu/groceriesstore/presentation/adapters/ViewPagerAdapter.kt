package com.hieuwu.groceriesstore.presentation.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.hieuwu.groceriesstore.R

class ViewPagerAdapter(val context: Context) : PagerAdapter() {

    private val images = arrayOf(
        "gs://groceries-store-ad0eb.appspot.com/slider-1.jpg",
        "gs://groceries-store-ad0eb.appspot.com/slider-1.jpg",
        "gs://groceries-store-ad0eb.appspot.com/slider-1.jpg",
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.layout_image_layout, null);
        val imageView = view.findViewById<ImageView>(R.id.imageView);
//        imageView.setImageResource(images[position]);
        Glide.with(view)
            .load(Uri.parse(images[position]))
            .into(imageView);
        container.addView(view, 0)
        return view
    }

    override fun getCount() = images.size


    override fun isViewFromObject(view: View, `object`: Any) = view == `object`


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}