package com.example.task.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_mvvm.R
import com.example.task.model.Product
import com.example.task.ui.listener.OnProductItemClick

class ProductAdapter(
    private var productList: List<Product>,
    private val listener: OnProductItemClick
) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.product_item, viewGroup, false)
        return ProductHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = productList.get(position);
        holder.initData(product)
        holder.shoppingCartContainer?.setOnClickListener {
            it.visibility = View.GONE
            holder.productCountContainer?.visibility = View.VISIBLE
            val animView: Animation =
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_right)
            holder.tvPrice?.startAnimation(animView)
            holder.productCountContainer?.startAnimation(animView)
        }
        holder.itemView.setOnClickListener { listener.onItemClick(product.id) }
    }

    class ProductHolder(v: View) : RecyclerView.ViewHolder(v) {
        var shoppingCartContainer: RelativeLayout? = null
        var productCountContainer: ConstraintLayout? = null
        var tvProductName: TextView? = null
        var tvPrice: TextView? = null
        var icProduct: ImageView

        init {
            shoppingCartContainer = itemView.findViewById(R.id.shopping_cart_container)
            productCountContainer = itemView.findViewById(R.id.product_count_container)
            tvProductName = itemView.findViewById(R.id.tv_product_name)
            tvPrice = itemView.findViewById(R.id.tv_price)
            icProduct = itemView.findViewById(R.id.ic_product)
        }

        fun initData(product: Product) {
            tvProductName?.text = product.productName
            val price = product.price.toString() + " Դ/կգ"
            tvPrice?.text = price
            loadImage(product)
        }

        private fun loadImage(product: Product) {
            Glide.with(itemView.context)
                .load(product.imageUrl)
                .into(icProduct)
        }
    }
}