package com.lemon.lib_base.data.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lemon.lib_base.base.BaseBean
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.http.HttpHeaders
import java.nio.charset.StandardCharsets

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        val responseBody = response.body()
        val url = request.url().toString()

        if (HttpHeaders.hasBody(response)) {
            if (responseBody == null) return response


            val contentType = responseBody.contentType()

            when (response.code()) {
                503, 504, 500, 404, 403, 400 -> {
                    response = response.newBuilder().code(response.code()).message("链接服务器失败，请稍后重试！")
                        .build()
                }
                200 -> {
                    val source = responseBody.source()
                    source.request(Long.MAX_VALUE)
                    val buffer = source.buffer()
                    val charsets = StandardCharsets.UTF_8
                    if (charsets != null) {
                        //可统一处理加解密过程
                        val bodyString = buffer.clone().readString(charsets)
                        val baseBean = Gson().fromJson<BaseBean<*>>(
                            bodyString,
                            object : TypeToken<BaseBean<*>?>() {}.type
                        )

                        when (baseBean.errorCode) {
                            -1001 -> {
                                // token 失效
                                // token/cookie失效 ApiSubscriberHelper 已在ApiSubscriberHelper网络业务层处理
                                // LiveBusCenter.postTokenExpiredEvent(baseBean.errorMsg)
                            }
                        }
                        val newRespBody = ResponseBody.create(contentType, bodyString)
                        response = response.newBuilder().body(newRespBody).build()
                    }
                }
            }
        }
        return response
    }
}