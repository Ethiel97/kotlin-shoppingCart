package com.example.ethieladiassa.shoppingcart

import io.paperdb.Paper

class ShoppingCart {


    companion object {


        fun addItem(cartItem: CartItem) {
            var cart = ShoppingCart.getCart()

            cart.forEach {
                if (it.product.id == cartItem.product.id)
                    cartItem.quantity++
                else
                    cartItem.quantity = 1
            }

            ShoppingCart.saveCart(cart)

        }

        fun removeItem(cartItem: CartItem) {

            var cart = ShoppingCart.getCart()

            cart.forEach {
                if (it.product.id == cartItem.product.id && it.quantity > 0) {
                    cartItem.quantity--
                    return@forEach
                }
            }

            ShoppingCart.saveCart(cart)

        }

        fun saveCart(cart: MutableList<CartItem>) {
            Paper.book().write("cart", cart)
        }


        fun getCart(): MutableList<CartItem> {
            return Paper.book().read("cart", mutableListOf())

        }
    }

}