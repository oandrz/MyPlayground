package com.example.core.networking

import com.example.core_api.MyPlaygroundNetwork
import retrofit2.Retrofit
import javax.inject.Inject

class MyPlaygroundNetworkImpl @Inject constructor(
    private val retrofit: Retrofit
) : MyPlaygroundNetwork {

    override fun <T> fromService(clazz: Class<T>): T = retrofit.create(clazz)
}