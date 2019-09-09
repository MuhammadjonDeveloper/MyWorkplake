package com.e.myworkplace.networks

import com.e.myworkplace.components.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var ourInstaince:Retrofit?=null
    private var apiService:ApiService?=null
    val instaince:Retrofit
    get() {
        if (ourInstaince==null) {
            ourInstaince=Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return ourInstaince!!
    }

    fun getApiService():ApiService?{
        return ourInstaince?.create(ApiService::class.java)
    }
}
