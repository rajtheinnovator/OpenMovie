package me.abhishekraj.openmovie.data.remote

import me.abhishekraj.openmovie.util.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Abhishek Raj on 6/19/2019.
 */

object APIClient {

    private var retrofit: Retrofit? = null

    val client: Retrofit
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()

            return retrofit!!
        }

}