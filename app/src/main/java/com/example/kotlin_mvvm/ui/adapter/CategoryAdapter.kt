package com.example.task.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_mvvm.R

import com.example.task.model.Category
import com.mikhaellopez.circularimageview.CircularImageView

class CategoryAdapter(private var categoryList: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category = categoryList.get(position);
        holder.initData(category)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CategoryHolder {
        val view =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.category_item, viewGroup, false)
        return CategoryHolder(view)
    }

    fun recyclerViewScrolled(
        recyclerView: RecyclerView,
        scrollAmount: Int
    ) {
        var holder: CategoryHolder?
        val startPos =
            (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val endPos =
            (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        for (i in startPos..endPos) {
            holder = recyclerView.findViewHolderForLayoutPosition(i) as CategoryHolder?
            holder?.rotateOfferView(scrollAmount)
        }
    }

    class CategoryHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvCategoryName: TextView? = null
        var tvProductCount: TextView? = null
        var icCategory: CircularImageView

        init {
            tvCategoryName = itemView.findViewById(R.id.tv_category_name)
            tvProductCount = itemView.findViewById(R.id.tv_product_count)
            icCategory = itemView.findViewById(R.id.ic_category)
        }

        fun initData(category: Category) {
            tvCategoryName?.text = category.categoryName
            val productCount = category.productCount.toString() + " products"
            tvProductCount?.text = productCount
            loadImage(category)
        }

        private fun loadImage(category: Category) {
            Glide.with(itemView.context)
                .load(category.imageUrl)
                .into(icCategory)
        }

        fun rotateOfferView(scrollAmount: Int) {
            icCategory.rotate(scrollAmount)
        }

        fun ImageView.rotate(scrollAmount: Int) {
            rotation += scrollAmount
        }
    }
}