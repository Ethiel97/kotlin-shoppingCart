package com.example.ethieladiassa.shoppingcart

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_row_item.view.*

class ProductAdapter(var context: Context, var products: List<Product> = arrayListOf()) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ProductAdapter.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ProductAdapter.ViewHolder, position: Int) {

        viewHolder.bindProduct(products[position])


        (context as MainActivity).coordinator

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("CheckResult")
        fun bindProduct(product: Product) {


            itemView.product_name.text = product.name
            itemView.product_price.text = "$${product.price.toString()}"

            Picasso.get().load(product.photos[0].filename).fit().into(itemView.product_image)


            //                val products = mutableListOf<Product>()
//                products.add(product)
//

//                ShoppingCart.addItem(item)


            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {


                itemView.addToCart.setOnClickListener { view ->

                    val item = CartItem(product)

                    ShoppingCart.addItem(item)
                    //notify users
                    Snackbar.make(
                        (itemView.context as MainActivity).coordinator,
                        "${product.name} added to your cart",
                        Snackbar.LENGTH_LONG
                    ).show()


                    it.onNext(ShoppingCart.getCart())

                }

                itemView.removeItem.setOnClickListener { view ->

                    val item = CartItem(product)

                    ShoppingCart.removeItem(item, itemView.context)

                    Snackbar.make(
                        (itemView.context as MainActivity).coordinator,
                        "${product.name} removed from your cart",
                        Snackbar.LENGTH_LONG
                    ).show()

                    it.onNext(ShoppingCart.getCart())

                }


            }).subscribe { cart ->

                var quantity = 0

                cart.forEach { cartItem ->

                    quantity += cartItem.quantity
                }


                (itemView.context as MainActivity).cart_size.text = quantity.toString()
                Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()


            }


            /* Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {


                 itemView.removeItem.setOnClickListener { view ->

                     val item = CartItem(product)

                     ShoppingCart.removeItem(item)

                     Snackbar.make(
                         (itemView.context as MainActivity).coordinator,
                         "${product.name} removed from your cart",
                         Snackbar.LENGTH_LONG
                     ).show()

                     it.onNext(ShoppingCart.getCart())

                 }


             }).subscribe { cart ->

                 var quantity = 0

                 cart.forEach { cartItem ->

                     quantity += cartItem.quantity
                 }


                 (itemView.context as MainActivity).cart_size.text = quantity.toString()
                 Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()


             }
 */

//                Toast.makeText(itemView.context, "${product.name} added to your cart", Toast.LENGTH_SHORT).show()


        }

    }

}