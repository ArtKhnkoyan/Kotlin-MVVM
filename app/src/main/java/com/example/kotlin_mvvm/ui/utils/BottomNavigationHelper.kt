package com.example.task.ui.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.IdRes
import com.example.kotlin_mvvm.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationHelper {

    fun showCustomShoppingCart(
        context: Context,
        bottomNavigationView: BottomNavigationView,
        @IdRes itemId: Int
    ) {
        val itemView = bottomNavigationView.findViewById<BottomNavigationItemView>(itemId)
        val badge = LayoutInflater.from(context)
            .inflate(R.layout.custom_shopping_cart_layout, bottomNavigationView, false)
        itemView.addView(badge)
    }

    fun showBadge(
        context: Context,
        bottomNavigationView: BottomNavigationView,
        @IdRes itemId: Int
    ) {
        val itemView = bottomNavigationView.findViewById<BottomNavigationItemView>(itemId)
        val badge = LayoutInflater.from(context)
            .inflate(R.layout.custom_notification_layout, bottomNavigationView, false)
        itemView.addView(badge)
    }
}