package com.example.task.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_mvvm.R
import com.example.kotlin_mvvm.ui.ProductDetailActivity
import com.example.task.ui.adapter.CategoryAdapter
import com.example.task.ui.adapter.ProductAdapter
import com.example.task.ui.listener.OnProductItemClick
import com.example.task.ui.utils.LANG
import com.example.task.ui.utils.PRODUCT_ID
import com.example.task.ui.utils.TOKEN
import com.example.task.viewModel.CategoryViewModel
import com.example.task.viewModel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), OnProductItemClick {
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var productViewModel: ProductViewModel

    private var categoryAdapter: CategoryAdapter? = null
    private var productAdapter: ProductAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recCategories.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int
                ) {
                    categoryAdapter?.recyclerViewScrolled(recyclerView, (dx / 4))
                }
            })
        }

        recProducts.apply {
            layoutManager = LinearLayoutManager(activity)
        }

        categoryViewModel.getTopCategories(TOKEN, LANG);
        productViewModel.getMostPopularProducts(TOKEN, LANG);

        categoryViewModel.categoryList.observe(viewLifecycleOwner, Observer {
            it?.let { categoryList ->
                categoryAdapter = CategoryAdapter(categoryList)
                recCategories.adapter = categoryAdapter
            }
        })

        productViewModel.productList.observe(viewLifecycleOwner, Observer {
            it?.let { productList ->
                productAdapter = ProductAdapter(productList, this)
                recProducts.adapter = productAdapter
            }
        })
    }

    override fun onItemClick(productId: Int) {
        activity?.let {
            val intent = Intent(it, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT_ID, productId)
            it.startActivity(intent)
            it.finish()
            it.overridePendingTransition(R.anim.slide_up, R.anim.nothing)
        }
    }
}