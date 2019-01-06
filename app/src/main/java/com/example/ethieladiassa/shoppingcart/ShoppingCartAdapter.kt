package com.example.ethieladiassa.shoppingcart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_list_item.view.*

class ShoppingCartAdapter(var context: Context, var cartItems: List<CartItem>) :
    RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ShoppingCartAdapter.ViewHolder {

        val layout = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(viewHolder: ShoppingCartAdapter.ViewHolder, position: Int) {

        viewHolder.bindItem(cartItems[position])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(cartItem: CartItem) {


            Picasso.get().load(cartItem.product.photos[0].filename).fit().into(itemView.product_image)


            itemView.product_name.text = cartItem.product.name

            itemView.product_price.text = "$${cartItem.product.price}"

            itemView.product_quantity.text = cartItem.quantity.toString()

        }


    }

}