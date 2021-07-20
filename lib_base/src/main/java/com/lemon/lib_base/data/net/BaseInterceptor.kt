package com.lemon.lib_base.data.net

import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 * 拦截器处理请求头：
 *
 * 1. 添加请求头
 */
class BaseInterceptor(private val headers: Map<String, String>?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newBuilder = chain.request().newBuilder()
        if (headers != null && headers.isNotEmpty()) {
            val keys = headers.keys
            for (headerKey in keys) {
                newBuilder.addHeader(headerKey, headers[headerKey]).build()
            }
        }
        return chain.proceed(newBuilder.build())
    }
}