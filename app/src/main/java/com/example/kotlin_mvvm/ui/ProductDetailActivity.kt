package com.example.kotlin_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.kotlin_mvvm.R
import com.example.task.ui.fragment.ProductDetailFragment
import com.example.task.ui.utils.PRODUCT_ID
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val productId = intent.getIntExtra(PRODUCT_ID, 0)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProductDetailFragment.newInstance(productId))
            .commit()

        fab_shoping.setOnClickListener {
            it.visibility = View.GONE
            constraint_add_to_basket.visibility = View.VISIBLE
            val animView: Animation =
                AnimationUtils.loadAnimation(this, R.anim.slide_left)
            constraint_add_to_basket.startAnimation(animView)
        }
    }
}