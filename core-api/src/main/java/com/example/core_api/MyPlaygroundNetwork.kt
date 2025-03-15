package com.example.core_api

interface MyPlaygroundNetwork {
    fun <T> fromService(clazz: Class<T>): T
}