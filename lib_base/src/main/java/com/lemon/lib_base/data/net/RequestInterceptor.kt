package com.lemon.lib_base.data.net

import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import java.net.URLDecoder.decode
import java.nio.charset.StandardCharsets

/**
 *
 * 请求拦截器：
 *
 * 1. 可以对请求的参数进行加密
 * 2. 对于请求的打印日志
 * 3.
 */
class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().toString()
        val method = request.method()

        if ("POST" == method) {
            val body = request.body()
            val builder = request.url().newBuilder()

            val buffer = Buffer()
            body?.writeTo(buffer)

//将请求数据进行utf-8进行编码
            val requestData = decode(buffer.readString(StandardCharsets.UTF_8), "utf-8")


            try {
                val requestBuilder = request.newBuilder();

                val newRequestBody = RequestBody.create(body?.contentType(), requestData)

                val build = requestBuilder.post(newRequestBody).url(builder.build()).build()
                return chain.proceed(build)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return chain.proceed(request)
    }
}