package com.example.task.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kotlin_mvvm.R
import com.example.kotlin_mvvm.ui.MainActivity
import com.example.task.model.Product
import com.example.task.ui.utils.LANG
import com.example.task.ui.utils.PRODUCT_ID
import com.example.task.ui.utils.TOKEN
import com.example.task.viewModel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product_detail.*

class ProductDetailFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val productId = requireArguments().getInt(PRODUCT_ID, 0)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.getProductById(TOKEN, LANG, productId);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel.product.observe(viewLifecycleOwner, Observer {
            it?.let { product ->
                tv_category_name.text = product.productName
                val price = product.price.toString() + " AMD"
                tv_product_price.text = price
                tv_product_description.text = product.description
                tv_ingredient_description.text = product.ingredients
                tv_shelf_description.text = product.shelfLife
                loadImage(product)
            }
        })

        ic_shift_down.setOnClickListener {
            activity?.let {
                val intent = Intent(it, MainActivity::class.java)
                it.startActivity(intent)
                it.finish()
                it.overridePendingTransition(R.anim.slide_down, R.anim.nothing)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(productId: Int) =
            ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(PRODUCT_ID, productId)
                }
            }
    }

    private fun loadImage(product: Product) {
        Glide.with(this)
            .load(product.imageUrl)
            .into(ic_product_item)
    }
}