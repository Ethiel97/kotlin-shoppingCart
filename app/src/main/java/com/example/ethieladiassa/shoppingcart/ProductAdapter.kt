package com.example.ethieladiassa.shoppingcart

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import io.paperdb.Paper
import kotlinx.android.synthetic.main.product_row_item.view.*

class ProductAdapter(var context: Context, var products: List<Product> = arrayListOf()) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductAdapter.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, null)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ProductAdapter.ViewHolder, position: Int) {

        viewHolder.bindProduct(products[position])

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindProduct(product: Product) {

            itemView.product_name.text = product.name
            itemView.product_price.text = "$${product.price.toString()}"

            Picasso.get().load(product.photos[0].filename).fit().into(itemView.product_image)

            itemView.addToCart.setOnClickListener {

                val item = CartItem(product)

                val products = mutableListOf<Product>()
                products.add(product)


                ShoppingCart.addItem(item)

                Toast.makeText(itemView.context, "${product.name} added to your cart", Toast.LENGTH_SHORT).show()


            }

        }

    }

}