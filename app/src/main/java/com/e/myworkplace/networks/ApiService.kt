package com.e.myworkplace.networks

import com.e.myworkplace.db.Task
import com.e.myworkplace.db.WordExample
import com.e.myworkplace.networks.req.LoginRequest
import com.e.myworkplace.networks.res.MyLoginResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("posts")
    fun posts(): Single<List<Task>>

    @POST("authenticate")
    fun getLogin(@Body loginRequest: LoginRequest):Single<MyLoginResponse>
}