package com.example.ethieladiassa.shoppingcart

import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_shopping_cart.*

class ShoppingCartActivity : AppCompatActivity() {

    lateinit var adapter: ShoppingCartAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)


        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material)
        upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)


        adapter = ShoppingCartAdapter(this, ShoppingCart.getCart())
        adapter.notifyDataSetChanged()

        shopping_cart_recyclerView.adapter = adapter

        shopping_cart_recyclerView.layoutManager = LinearLayoutManager(this)


        var totalPrice = ShoppingCart.getCart()
            .fold(0.toDouble()) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.product.price!!.toDouble()) }


        total_price.text = "$${totalPrice}"
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()

            }
        }

        return super.onOptionsItemSelected(item)
    }
}
