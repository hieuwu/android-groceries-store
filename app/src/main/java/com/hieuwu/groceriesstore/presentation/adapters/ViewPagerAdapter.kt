package com.hieuwu.groceriesstore.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.presentation.utils.bindImage

class ViewPagerAdapter(val context: Context) : PagerAdapter() {

    private val images = arrayOf(
        "https://firebasestorage.googleapis.com/v0/b/shopee-93233.appspot.com/o/product_image1664736648315.png?alt=media&token=2ca8be3a-37c3-4c73-b966-dcf3129958fd",
        "https://firebasestorage.googleapis.com/v0/b/shopee-93233.appspot.com/o/product_image1664737376188.png?alt=media&token=01123878-812e-4b2b-be91-d9c05b1e9b98",
        "https://firebasestorage.googleapis.com/v0/b/shopee-93233.appspot.com/o/product_image1664737421330.png?alt=media&token=66f45505-ce68-4583-b018-2d2855aa714e"
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.layout_image_layout, null)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        bindImage(imageView, images[position])
        container.addView(view, 0)

        return view
    }

    override fun getCount() = images.size

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
