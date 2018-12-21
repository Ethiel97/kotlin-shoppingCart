package com.example.ethieladiassa.shoppingcart

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.File
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import retrofit2.converter.scalars.ScalarsConverterFactory


object APIConfig {

    val BASE_URL = "https://all-spices.com/api/products/"

    private var retrofit: Retrofit? = null


    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofitClient(context: Context): Retrofit {

        //    File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
        //    Cache cache = new Cache(httpCacheDirectory, 70 * 1024 * 1024);

        val okHttpClient = OkHttpClient.Builder()
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                        .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }
}
