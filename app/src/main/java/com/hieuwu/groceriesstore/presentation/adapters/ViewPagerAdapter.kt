package com.hieuwu.groceriesstore.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.hieuwu.groceriesstore.R

class ViewPagerAdapter(val context: Context) : PagerAdapter() {

    private val images = arrayOf(
        R.drawable.banner,
        R.drawable.banner,
        R.drawable.banner
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.layout_image_layout, null);
        val imageView = view.findViewById<ImageView>(R.id.imageView);
        imageView.setImageResource(images[position]);

        container.addView(view, 0);
        return view
    }

    override fun getCount() = images.size


    override fun isViewFromObject(view: View, `object`: Any) = view == `object`


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}